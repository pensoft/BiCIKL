# Enhance the GBIF clustering algorithms

## Partners
- GBIF (lead) @Tim Robertson
- TDWG
- Naturalis

## Infrastructures to be used 
- GBIF 
## Rationale
Metadata about digitised specimens, sequences and material citations and observations are made available through a consistent data model in GBIF.org. The features within the model are structured using the Darwin Core vocabularies. GBIF now runs a basic clustering algorithm to identify linkages across these entries, which currently covers approximately 100M records, including 6M specimens (see https://www.gbif.org/news/4U1dz8LygQvqIywiRIRpAU). There is much that can be done to improve this current algorithm to identify more linkages, such as 1) handling variety in how people (names and IDs) are represented, 2) better parsing of botanical field numbers, 3) exploring different ML approaches (such as LinkedIn SCANNS https://github.com/LinkedInAttic/scanns) and 4) develop an approach to testing to quantify if improvements are being made over time. GBIF develops openly and it is expected that further collaborative work could continue beyond the hackathon. Improvements made to the algorithm will be put into production and run as part of ongoing operations at GBIF.

## Methodology

## Outputs
- A distributed team of collaborators contributing to the production workflows of GBIF is seen as the main output (i.e. team building)
- Knowledge sharing of GBIF operations; namely the big data processing environments used by GBIF and file formats (Spark, Hive, HBse, Parquet etc)
- More linkages between objects through the GBIF APIs (material citations, specimens, sequences)
- A more robust mechanism to monitor ongoing improvements to the GBIF clustering

