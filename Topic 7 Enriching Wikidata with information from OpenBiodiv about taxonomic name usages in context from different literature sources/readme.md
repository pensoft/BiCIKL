# Enriching Wikidata with information from OpenBiodiv about type specimens in context from different literature sources

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
Taxonomic literature is a rich source of biodiversity knowledge. The structure of taxonomic journal articles generally follows established rules for formatting of taxonomic treatments (e.g., description of new taxa or re-description of the existing ones), as well as other generic parts such as title, abstract, keywords, introduction, results and so on. These rules, as well as the XML-tagging implemented in the routine publication process at **Pensoft**, have allowed us to create and populate the **OpenBiodiv** knowledge graph with Linked Open Data statements extracted from literature, following the OpenBiodiv-O ontology. A particularly valuable source of biodiversity knowledge in scholarly literature is the Materials Examined section which contains information about examined materials (type specimens). Most of the information originates from articles published in the Biodiversity Data Journal (BDJ). Within OpenBiodiv, statements about the Materials Examined section use properties from Darwin Core, such as dwc:recordedBy, dwc:habitat, dwc:locality and others. Together with institution and collection data, these statements can help us "build" a digital specimen. 

Wikidata is another open source of biodiversity information, which contains not only information about taxonomic names and hierarchy, but also external identifiers (links) about a specific taxon. For example, the Wikidata record for the beetle genus [Elater](https://www.wikidata.org/wiki/Q13033998) has 17 such identifiers to records from databases such as Zoobank, GBIF, iNaturalist, NCBI Taxonomy, Plazi, BOLD Systems, etc. In essence, these links contain information about occurrences, treatments, images and molecular data about specimens. Knowledge about type materials from OpenBiodiv integrated with existing **Wikidata** records can add another piece to the puzzle by adding more literature data.

## Methodology
We will perform SPARQL queries to OpenBiodiv to explore collections which have been used in the description of taxa using type specimens. We will examine whether the most prominent collections are featured on Wikidata and if not, we will suggest to create new pages for them. This would be the first step towards enriching Wikidata with collection data from OpenBiodiv. We could also explore which types of information about a type specimen available on OpenBiodiv, could be fed into Wikidata based on the existing Wikidata properties. This research would indicate properties which could be proposed in the future to help depict biodivesity data more thoroughly on Wikidata. In addition, our work on type specimens in OpenBiodiv could help us identify "hidden women" amongst people who collected or identified materials, thereby allowing us to provide insights to the team working on Topic 9.
We can also perform federation between the Wikidata and OpenBiodiv SPARQL endpoints to match taxonomic names in the two databases and to find differences. Matching could be performed not only between the names themselves but also between the accompanying **GBIF** taxon identifiers (the GBIF taxonomic backbone is RDF-ized in OpenBiodiv), **Zoobank** identifiers and/or **Plazi** treatment identifiers. 

## Outputs
- Analysis of under-represented collections in Wikidata
- Identification of candidate Wikidata properties to better model knowledge about biodiversity collections
- Established workflow for federation between OpenBiodiv and Wikidata
