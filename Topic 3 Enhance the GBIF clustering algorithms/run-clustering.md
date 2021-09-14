# Notes for running the clustering

The project Jar needs to be built using `mvn clean package`.

It can then be added to a running DataBricks cluster as a library.

The following is an example of how to launch the clustering process in a Scala notebook.

```
import org.gbif.pipelines.clustering.Cluster

val args = Array("--hive-db", "gbif", "--hive-table-hashed", "tim_hash", "--hive-table-candidates", "tim_candidates", "--hive-table-relationships", "tim_relationships")
Cluster.main(args)
```

The result will be new tables in the database.