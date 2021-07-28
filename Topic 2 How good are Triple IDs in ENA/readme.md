# How good are Triple IDs in ENA?

## Partners
- BGBM (lead)
- ENA
- [Global Biodiversity Information Facility](https://www.gbif.org/) (GBIF)

## Infrastructures to be used 

## Rationale
Large sets of records in the **European Nucleotide Archive (ENA)** reference specimens (e.g., as specimen_voucher, bio_material, or culture_collection) through the use of GBIF triple IDs. However, it is unclear whether these links correctly and reliably reference specimen records in **GBIF**. During the hackathon, we want to investigate these links in more detail and develop automated methods for their validation and correction.

## Methodology
We will first query all triple IDs present in the different object categories in ENA using the ENA API. We will then run these against the GBIF API and determine the number of exact matches as well as the number of approximate matches. For the approximate hits, we first need to define an appropriate similarity measure.
For the similar hits and triple IDs that have multiple potential hits, we will use e.g. Openrefine to compare metadata from GBIF, metadata from the primary sources (e.g. via CETAF IDs) with the specimen data stored in ENA and propose a procedure for automatically correcting/annotating triple IDs in ENA.

## Outputs
- a better understanding of the quality of Triple ID references in ENA.
- methods for validation of triple IDs in ENA and auto-correction/annotation.
