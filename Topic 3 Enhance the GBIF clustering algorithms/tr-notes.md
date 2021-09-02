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

