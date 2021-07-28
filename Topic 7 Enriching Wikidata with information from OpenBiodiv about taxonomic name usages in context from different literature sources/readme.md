# Enriching Wikidata with information from OpenBiodiv about taxonomic name usages in context from different literature sources

## Partners
- Pensoft (lead) @Mariya Dimitrova
- OpenBiodiv
- Wikidata
- GBIF
- Plazi
- Zoobank
## Infrastructures to be used 
- Wikidata
- GBIF
- OpenBiodiv SPARQL endpoints
  
## Rationale
Taxonomic literature is a rich source of biodiversity knowledge. The structure of taxonomic journal articles generally follows established rules for formatting of taxonomic treatments (e.g., description of new taxa or re-description of the existing ones), as well as other generic parts such as title, abstract, keywords, introduction, results and so on. These rules, as well as the XML-tagging implemented in the routine publication process at **Pensoft**, have allowed us to create and populate the **OpenBiodiv** knowledge graph with Linked Open Data statements extracted from literature, following the OpenBiodiv-O ontology. One of the key types of information within OpenBiodiv are taxonomic names usages (TNUs): mentionings of taxonomic names within different sections of the journal article. On its own, this information is valuable because it indicates the importance of a given taxonomic name in a specific article. Furthermore, co-mentionings of several taxa together, or of a taxon name and a habitat or environmental term can imply a relatedness between these. The incorporation with other information (geolocation data, ecology data, etc.) can provide additional context to TNUs in literature. Currently, OpenBiodiv has indexed 4 993 677 usages of 2 497 310 taxon names. 

Wikidata is another open source of biodiversity information, which contains not only information about taxonomic names and hierarchy, but also external identifiers (links) about a specific taxon. For example, the Wikidata record for the beetle genus [Elater](https://www.wikidata.org/wiki/Q13033998) has 17 such identifiers to records from databases such as Zoobank, GBIF, iNaturalist, NCBI Taxonomy, Plazi, BOLD Systems, etc. In essence, these links contain information about occurrences, treatments, images and molecular data about specimens. Taxonomic name usages from OpenBiodiv integrated with existing **Wikidata** records can add another piece to the puzzle by adding more literature data.

## Methodology
We will perform federation between the Wikidata and OpenBiodiv SPARQL endpoints to match taxonomic names in the two databases. Matching could be performed not only between the names themselves but also between the accompanying **GBIF** taxon identifiers (the GBIF taxonomic backbone is RDF-ized in OpenBiodiv), **Zoobank** identifiers and/or **Plazi** treatment identifiers. We will aggregate information about TNUs within article sections (Title, Abstract, Introduction, Treatment, Materials Examined, Distribution, etc.) and provide it to Wikidata. One of the key questions is how to model this information as Wikidata statements. One approach would be to provide statistical information in one set of statements (number of TNUs per section) as well as article metadata (DOI) in another set of statements to answer the question 'In which specific article is a taxonomic name mentioned?'. In addition, we could add statements about co-mentioning of several taxonomic names together (e.g. in the Treatment section)

## Outputs
- Statements in Wikidata about taxonomic name usages in context from different literature sources
- Established workflow for federation between OpenBiodiv and Wikidata
