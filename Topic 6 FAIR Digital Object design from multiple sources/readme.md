# FAIR Digital Object design for data from multiple sources 

## Partners
- Naturalis (lead) @Sharif Islam @Wouter Addink
- TDWG
- GBIF
- ENA
- OpenBiodiv
- DiSSCo

## Infrastructures to be used 

## Rationale
FAIR Digital Objects (FDO) are actionable Digital Objects with persistent identifiers and properties. It provides a mechanism for abstract data constructs that can span multiple domains and data sources. It can also create abstractions for different segmentations within a data intensive workflow with identifiers and data type validation. 

These and other properties make FDO an ideal candidate to explore and link different biodiversity data classes such as taxon, specimens, sequences. However, conceptual knowledge encoded in domain ontologies and vocabularies are known obstacles for data reference, linking, and merging. FDO can work as an anchor for creating an interoperable digital object in this case.  

The goal of this task is to explore ideas and methods to create FAIR Digital Objects that can provide a schema to either connect and link or combine the disparate digital objects within a coherent structure. 

### Example
[GBIF occurrence data](https://api.gbif.org/v1/occurrence/2443791360) for *Isabellaria isabellina isabellina* and **Naturalis Bioportal** catalog records (for example, [RMNH.MOL.47311](https://api.biodiversitydata.nl/v2/specimen/findByUnitID/RMNH.MOL.47311) and [RMNH.MOL.47312](https://api.biodiversitydata.nl/v2/specimen/findByUnitID/RMNH.MOL.47312)) and related [ENA record](https://www.ebi.ac.uk/ena/browser/api/embl/AY382076.1) are about the preserved specimens for the same organism. There are also objects describing the scientific name ([openbiodiv](http://openbiodiv.net/F1DD0CF0-217D-422B-BAA4-58901976D7B4-4570425-scName) and [Catalogue of Life](https://data.catalogueoflife.org/dataset/2296/taxon/7GXFW)) using different data structures. These objects all have slightly different data elements that introduce semantic heterogeneity and structural differences. For example, the Naturalis Bioportal primarily uses ABCD terms (such as [RecordBasis](https://terms.tdwg.org/wiki/abcd2:RecordBasis)) and GBIF uses Darwin Core ([basisOfRecord](https://dwc.tdwg.org/terms/#dwc:basisOfRecord)).  Even though there are mapped terms ([CODATA / TDWG Task Group on Access to Biological Collection Data](https://www.bgbm.org/TDWG/CODATA/Schema/Mappings/DwCAndExtensions.htm)), linking, merging different structures is not that trivial. Furthermore, adding sequence and taxonomic data to the mix complicates the matter further. 



## Methodology
Propose different data elements from at least two different data sources that are describing similar or related objects. They could be linked via identifiers or other entities or not. By looking at the content of these objects (possibly programmatically) a schema label will be generated. As a lot of data linking requires common information or identifiers, unique schema labels are needed. Each data source has their own schema label even if they refer to the same concept. In the above example, The Naturalis record has 
``"recordBasis": "PreservedSpecimen"`` but GBIF has ``"basisOfRecord": “PRESERVED_SPECIMEN"``;  ``"className": "Gastropoda"`` vs ``“class": "Gastropoda"``; 
``"sourceInstitutionID": "Naturalis Biodiversity Center"`` vs ``"institutionCode": "Naturalis"``; ``"preparationType: air dried"`` vs ``“preparations: air dried"``. 

We will evaluate several approaches to process the mapping of these schemas based on semantic similarity:
- A simple framework would be to model entities/classes as n-grams and define similarity based on the Levenstein distance
- If classes are DwC/ABCD classes or could be mapped to those (using the method above), more advanced methods of graph matching for the RDF representation of DwC (https://dwc.tdwg.org/rdf/) and ABCD (v 3.0 https://abcd.biowikifarm.net/wiki/Main_Page) could be applied (e.g. Graph Convolutional Networks).
- A major step forward for the harmonisation of data schemas will be the integration of DwC/ABCD terms as subclasses of a domain ontology like the Biological Collections Ontology (BCO). Recent efforts in this respect are dwcobo ([BiodiversityOntologies/dwcobo: code for creating in Darwin Core ontology modules to import to BCO](https://github.com/BiodiversityOntologies/dwcobo)) and DiSSCo’s open Digital Specimen (opeDS) ontology ([openDS/ods-ont-intro.md at master · DiSSCo/openDS](https://github.com/DiSSCo/openDS/blob/master/ods-ontology/ods-ont-intro.md)). A full representation in OWL would enable more complex methods of symbolic (e.g. LogMap-based alignment) and combined symbolic/subsymbolic approaches like OPA2Vec (https://github.com/bio-ontology-research-group/machine-learning-with-ontologies). 


## Outputs
- A framework and recipe to label schema and align terms. 
- A new FAIR Digital Object schema and corresponding Data Types.
- A prototype of a Digital Object repository (based on cordra) for the new FAIR DO type.   