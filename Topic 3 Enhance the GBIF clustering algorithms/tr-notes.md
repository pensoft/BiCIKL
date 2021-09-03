# Personal notes from Tim Robertson

These are notes to self, which will be moved into documentation when ready.


1. Create the table as all GBIF minus eBird (to reduce volume) on GBIF:

```
SET hive.exec.compress.output=true; 
SET avro.output.codec=snappy;
SET hive.avro.output.codec=snappy;
SET hive.avro.output.codec=snappy;
SET avro.output.codec=snappy;
SET hive.merge.mapredfiles=true;
SET hive.merge.mapfiles=true;
SET hive.mapreduce.job.reduces=100;
 
CREATE TABLE tim.hackathon 
STORED AS AVRO
AS SELECT
  gbifId, datasetKey, basisOfRecord, publishingorgkey, datasetName, publisher,
  kingdomKey, phylumKey, classKey, orderKey, familyKey, genusKey, speciesKey, acceptedTaxonKey, taxonKey,
  scientificName, acceptedScientificName, kingdom, phylum, order_ AS order, family, genus, species, genericName, specificEpithet, taxonRank,
  typeStatus, preparations,
  decimalLatitude, decimalLongitude, countryCode,
  year, month, day, from_unixtime(floor(eventDate/1000)) AS eventDate,
  recordNumber, fieldNumber, occurrenceID, otherCatalogNumbers, institutionCode, collectionCode, catalogNumber,
  recordedBy, recordedByID,
  ext_multimedia
FROM prod_h.occurrence
WHERE speciesKey IS NOT NULL AND datasetKey != '4fa7b334-ce0d-4e88-aaae-2e0c138d049e'
GROUP BY 
gbifId, datasetKey, basisOfRecord, publishingorgkey, datasetName, publisher,
  kingdomKey, phylumKey, classKey, orderKey, familyKey, genusKey, speciesKey, acceptedTaxonKey, taxonKey,
  scientificName, acceptedScientificName, kingdom, phylum, order_, family, genus, species, genericName, specificEpithet, taxonRank,
  typeStatus, preparations,
  decimalLatitude, decimalLongitude, countryCode,
  year, month, day, from_unixtime(floor(eventDate/1000)),
  recordNumber, fieldNumber, occurrenceID, otherCatalogNumbers, institutionCode, collectionCode, catalogNumber,
  recordedBy, recordedByID,
  ext_multimedia;
```


2. Export from HDFS on GBIF:

```
cd /mnt/auto/scratch/trobertson
hdfs dfs -get /user/hive/warehouse/tim.db/hackathon/* .
```

3. Upload to AZ using the CLI

Need to create the container (hackathon) and create a time limited sas token.
Note: Be careful to put it in EUROPE!

```
az storage blob upload-batch -d hackathon/gbif/20210902/occurrence.avro \
-s ./  --sas-token 'generated in the UI' --account-name biciklhackathon --max-connections 16
```

4. Create a SAS to read this

In databricks we'll read this using SAS based authentication. Note that the SAS needs to be created with LIST permission or else a occurrence.avro/* can't work.

5. Now create a table in Databricks from the source data

```
val containerName = "hackathon"
val storageAccountName = "biciklhackathon"
val sas = "..." 
val config = "fs.azure.sas." + containerName+ "." + storageAccountName + ".blob.core.windows.net"

val fileName = "wasbs://" + containerName + "@biciklhackathon.blob.core.windows.net/gbif/20210902/occurrence.avro"

dbutils.fs.mount(
  source = fileName,
  mountPoint = "/mnt/test",
  extraConfigs = Map(config -> sas))

val sourceDF = spark.read.format("avro").load("/mnt/test3")
display(sourceDF)

sourceDF.write.format("parquet").saveAsTable("timtest")
val readTest = spark.sql("SELECT gbifID from timtest")
display(readTest)


dbutils.fs.unmount("/mnt/test")
```