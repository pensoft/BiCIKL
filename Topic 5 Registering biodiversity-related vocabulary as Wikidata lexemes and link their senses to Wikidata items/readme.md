# Registering biodiversity-related vocabulary as Wikidata lexemes and link their senses to Wikidata items

## Partners
- [Pensoft](https://pensoft.net/) (lead)
- [Wikidata](https://wikidata.org/)
- [Plazi](http://plazi.org/)
- [COL](https://www.catalogueoflife.org/)

## Infrastructures to be used 
- Wikidata
- COL

## Rationale
Content mining involves a combination of methods that take into account knowledge about
* the documents being mined
* the languages used therein
* the documents’ content and
* specific use cases at hand. 
While special workflows exist for mining biodiversity information (e.g. Plazi), it would be desirable to have such workflows more readily available to and reusable by a broader community and to invite community members to help curate the underlying information. Integration of such workflows with Wikidata would be a good step forward in this direction.

## Methodology
Wikidata is a [FAIR](https://en.wikipedia.org/wiki/FAIR_data) and open semantic database that is community-curated following the Wikipedia approach that “anyone can edit it”. Besides information about concepts and their relationship between each other and the wider world (known as **[items](https://www.wikidata.org/wiki/Help:Items)** and **[properties](https://www.wikidata.org/wiki/Help:Properties)** in Wikidata parlance, largely equivalent to the [**subjects** and **predicates**](https://en.wikipedia.org/wiki/Semantic_triple) in semantic web lingo), Wikidata has also begun to [store structured information about languages and their components](https://www.wikidata.org/wiki/Wikidata:Lexicographical_data), which it calls **[lexemes](https://www.wikidata.org/wiki/Wikidata:Lexicographical_data/Documentation#Lexeme)**. The idea of this hackathon project would be to
1. pick a set of documents - possibly some that other groups at the hackathon are analyzing or producing, such as taxonomic publications or taxon treatments - or similar resources in one or more target languages and 
1. use natural language processing to 
    1. extract biodiversity-related words and phrases
    1. upload such vocabulary as lexemes to Wikidata
    1. curate those lexeme entries by adding information about their **[forms](https://www.wikidata.org/wiki/Wikidata:Lexicographical_data/Documentation#Form)**, **[senses](https://www.wikidata.org/wiki/Wikidata:Lexicographical_data/Documentation#Sense)** and usages, and
1. make provisions to scale this up.
1. OPTIONAL: Use visualizations via [Ordia](https://ordia.toolforge.org/) to assist with quality control, prioritization and data exploration.

## Outputs
The minimal outcome of this hackathon project would be the documentation of a workflow - for at least one BiCIKL infrastructure - that covers in detail the steps listed under “2” in the methodology section above, ideally based on use cases (as per “1”) for which automation (as per “3”) is within reach. In the long run, doing this systematically for one or more of the BiCIKL research infrastructures will help disambiguate ambiguous terms better and recognize biodiversity-related entities with higher precision across a growing range of linguistic and thematic contexts and document types. Such improved workflows can help improve the information in Wikidata, thereby providing the basis for a positive data quality feedback loop between content mining and Wikidata-based curation of linguistic and subject-matter contexts for such content.

## Interested individuals
*Please list your name, link it to your GitHub profile and say a few words about what you'd like to do in the framework of this hackathon. Feel free to add further information, e.g. ORCID, Wikimedia user name or social media handles.*
- [Daniel Mietchen](https://github.com/Daniel-Mietchen) ([0000-0001-9488-1870](https://orcid.org/0000-0001-9488-1870), [User:Daniel Mietchen](https://www.wikidata.org/wiki/User:Daniel_Mietchen), [@EvoMRI](https://twitter.com/EvoMRI)): I plan to focus on vocabulary related to species that are invasive, endangered, recently extinct or newly described.
- you?
