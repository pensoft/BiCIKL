# Registering biodiversity-related vocabulary as Wikidata lexemes and link their senses to Wikidata items

## Partners
- Pensoft (lead)
- Wikidata
- Plazi
- COL

## Infrastructures to be used 
- Wikidata
- COL
  
## Rationale
Content mining involves a combination of methods that take into account knowledge about (i) the documents being mined, (ii) the languages used therein, (iii) the documents’ content and (iv) specific use cases at hand. While special workflows exist for mining biodiversity information (e.g. Plazi), it would be desirable to have such workflows more readily available to and reusable by a broader community and to invite community members to help curate the underlying information. Integration of such workflows with Wikidata would be a good step forward in this direction.

## Methodology
Wikidata is a FAIR and open semantic database that is community-curated following the Wikipedia approach that “anyone can edit it”. Besides information about concepts and their relationship between each other and the wider world (known as **items** and **properties** in Wikidata parlance, largely equivalent to the **subjects** and **predicates** in semantic web lingo), Wikidata has also begun to [store structured information about languages and their components](https://www.wikidata.org/wiki/Wikidata:Lexicographical_data), which it calls **lexemes**. The idea of this hackathon project would be to (i) pick a set of documents - possibly some that other groups at the hackathon are analyzing or producing, such as taxonomic publications or taxon treatments - or similar resources in one or more target languages and (ii) use natural language processing to (iia) extract biodiversity-related words and phrases, (iib) upload such vocabulary as lexemes to Wikidata, (iic) curate those lexeme entries by adding information about their grammar, **senses** and usages, and (iii) make provisions to scale this up.

## Outputs
The minimal outcome of this hackathon project would be the documentation of a workflow - for at least one BiCIKL infrastructure - that covers in detail the steps listed under “ii” in the methodology section above, ideally based on use cases (as per “i”) for which automation (as per “iii”) is within reach. In the long run, doing this systematically for one or more of the BiCIKL research infrastructures will help disambiguate ambiguous terms better and recognize biodiversity-related entities with higher precision across a growing range of linguistic and thematic contexts and document types. Such improved workflows can help improve the information in Wikidata, thereby providing the basis for a positive data quality feedback loop between content mining and Wikidata-based curation of linguistic and subject-matter contexts for such content.