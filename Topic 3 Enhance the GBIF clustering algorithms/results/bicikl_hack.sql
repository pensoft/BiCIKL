-- Databricks notebook source
select * from gbif.relationships_triple where id1 in (3349806846, 1264931755)

-- COMMAND ----------

-- ENA records in clusters before/after
select --count(distinct gbifid) as total_recs, 
  count(distinct bef.id1) as recs_clustered_b, count(distinct aft.id1) as recs_clustered,
  count(distinct bef.id2) as recs_linked_b, count(distinct aft.id2) as recs_linked
from gbif.occurrence 
left join gbif.relationships1 bef on bef.id1=gbifid
left join gbif.relationships_triple aft on aft.id1=gbifid
where datasetkey in ('d8cd16ba-bb74-4420-821e-083f2bac17c2', '393b8c26-e4e0-4dd0-a218-93fc074ebf4e', '583d91fe-bbc0-4b4a-afe1-801f88263016')

-- COMMAND ----------

-- ENA numbers grouped by dataset
select Left(title, 100) as Dataset, IfNull(bef.records, 0) as recs_clustered_b, aft.records as recs_clustered, 
  IfNull(bef.records_linked, 0) as recs_linked_b, aft.records_linked as recs_linked
from
  (select dataset2, count(distinct id1) as records, count(distinct id2) as records_linked
  from gbif.relationships1 where dataset1 in ('d8cd16ba-bb74-4420-821e-083f2bac17c2', '393b8c26-e4e0-4dd0-a218-93fc074ebf4e', '583d91fe-bbc0-4b4a-afe1-801f88263016')
  group by dataset2) as bef
full outer join
  (select dataset2, count(distinct id1) as records, count(distinct id2) as records_linked
  from gbif.relationships_triple where dataset1 in ('d8cd16ba-bb74-4420-821e-083f2bac17c2', '393b8c26-e4e0-4dd0-a218-93fc074ebf4e', '583d91fe-bbc0-4b4a-afe1-801f88263016')
  group by dataset2) as aft
on bef.dataset2=aft.dataset2
join gbif.datasets on aft.dataset2=dataset_key
order by recs_clustered desc

-- COMMAND ----------

-- iBOL totals
select --count(distinct gbifid) as total_recs, 
  count(distinct bef.id1) as recs_clustered_b, count(distinct aft.id1) as recs_clustered_after,
  count(distinct bef.id2) as recs_linked_b, count(distinct aft.id2) as recs_linked_after
from gbif.occurrence 
left join gbif.relationships1 bef on bef.id1=gbifid
left join gbif.relationships_triple aft on aft.id1=gbifid
where datasetkey='040c5662-da76-4782-a48e-cdea1892d14c'

-- COMMAND ----------

-- iBOL
select Left(title, 100) as Dataset, IfNull(bef.records, 0) as recs_clustered_b, aft.records as recs_clustered, 
  IfNull(bef.records_linked, 0) as recs_linked_b, aft.records_linked as recs_linked
from
  (select dataset2, count(distinct id1) as records, count(distinct id2) as records_linked
  from gbif.relationships1 where dataset1='040c5662-da76-4782-a48e-cdea1892d14c' group by dataset2) as bef
full outer join
  (select dataset2, count(distinct id1) as records, count(distinct id2) as records_linked
  from gbif.relationships_triple where dataset1='040c5662-da76-4782-a48e-cdea1892d14c' group by dataset2) as aft
on bef.dataset2=aft.dataset2
join gbif.datasets on aft.dataset2=dataset_key
order by recs_clustered desc

-- COMMAND ----------

-- Plazi totals
select --count(distinct gbifid) as total_recs, 
  count(distinct bef.id1) as recs_clustered_b, count(distinct aft.id1) as recs_clustered,
  count(distinct bef.id2) as recs_linked_b, count(distinct aft.id2) as recs_linked
from gbif.occurrence 
left join gbif.relationships1 bef on bef.id1=gbifid
left join gbif.relationships_triple aft on aft.id1=gbifid
where publishingorgkey='7ce8aef0-9e92-11dc-8738-b8a03c50a862'

-- COMMAND ----------

-- Plazi
select Left(aft.publishing_organization_title, 100) as Publisher, 
  IfNull(bef.records, 0) as recs_clustered_b, aft.records as recs_clustered, 
  IfNull(bef.records_linked, 0) as recs_linked_b, aft.records_linked as recs_linked
from
  (select d2.publishing_organization_title, count(distinct id1) as records, count(distinct id2) as records_linked
   from gbif.datasets d1
   join gbif.relationships1 on dataset1 = d1.dataset_key 
   join gbif.datasets d2 on dataset2=d2.dataset_key
   where d1.publishing_organization_key='7ce8aef0-9e92-11dc-8738-b8a03c50a862'
   group by d2.publishing_organization_title) as bef
full outer join
  (select d2.publishing_organization_title, count(distinct id1) as records, count(distinct id2) as records_linked
   from gbif.datasets d1
   join gbif.relationships_triple on dataset1 = d1.dataset_key 
   join gbif.datasets d2 on dataset2=d2.dataset_key
   where d1.publishing_organization_key='7ce8aef0-9e92-11dc-8738-b8a03c50a862'
   group by d2.publishing_organization_title) as aft
on bef.publishing_organization_title=aft.publishing_organization_title
order by recs_clustered desc


-- COMMAND ----------


