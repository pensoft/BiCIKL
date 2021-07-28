
# Assigning latin scientific names to OTUs based on sequence clusters

## Partners
- GBIF (lead) @Thomas Jeppesen
- UNITE/PlutoF
- COL
## Infrastructures to be used 
- GBIF
- COL
## Rationale
Curated sequence databases are important tools in modern taxonomy. They are used to identify sequences at the operational taxonomic unit (OTU) level. OTUs are usually represented by some stable identifier, such as the Species Hypothesis (SH) in **UNITE** or the Barcode Index Number (BIN) in the **Barcode of Life Data System (BOLD)**. In principle, these identifiers represent Species/Taxon concepts. In order to answer the question "What species does this sequence represent?" a linkage from an OTU identifier to a latin scientific name is needed. The taxon name for an OTU in the reference database has to somehow be derived from the taxonomic annotation of the sequences constituting the OTU. Currently, BOLD does not provide a single consensus taxon name for each BIN. In order to apply a taxon name, users therefore have to inspect all taxonomic annotations within the BIN and pick one. When blasting a single or a few sequences, this approach may suffice. However, in a taxonomic classification pipeline for many sequences, e.g. metabarcoding, this approach is impossible. Similarly, In order to place BINs or SHs into classic taxonomies such as the **GBIF** backbone taxonomy or the **Catalogue of Life** all bins must be unambiguously linked to a (parent) taxon.

Therefore an algorithm for selecting a taxon name for an OTU is important for linking molecular data to the world of museum specimens, treatments, etc.

## Methodology
Explore the algorithms for taxonomic assignment currently used by UNITE/PlutoF and the International Barcode of Life project (iBOL) Barcode Index Numbers (BINs) dataset in GBIF https://www.gbif.org/dataset/4cec8fef-f129-4966-89b7-4f8439aba058 and discuss shortcomings and advantages.

Explore improvements based on the underlying data. See https://dx.doi.org/10.3390%2Fmicroorganisms8121910
## Outputs
New algorithm, or improvements for existing algorithms for assigning latin scientific names to sequence based OTUs.

