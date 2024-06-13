# Spark Job implemented as Spring cloud task

## References
* Running on Kubernetes [**`running-on-kubernetes`**](https://spark.apache.org/docs/3.4.1/running-on-kubernetes.html#cluster-mode).
* Running Spark Job from a Spring boot app [**`spring-boot-spark-on-minikube`**](https://www.itaydafna.dev/blog/spring-boot-spark-on-minikube/).
* Spark job submit, jar from local filesystem to minikube [**`spark-and-local-filesystem-in-minikube`**](https://jaceklaskowski.github.io/spark-kubernetes-book/demo/spark-and-local-filesystem-in-minikube/).

## Commonly used commands

```shell
./bin/docker-image-tool.sh -r officiallysingh -t 3.4.1 build
./bin/docker-image-tool.sh -r officiallysingh -t 3.4.1 push
minikube image load officiallysingh/spark:3.4.1

kubectl create serviceaccount spark
kubectl create clusterrolebinding spark-role --clusterrole=edit --serviceaccount=default:spark --namespace=default

./bin/spark-submit \
    --master k8s://https://127.0.0.1:62979 \
    --deploy-mode cluster \
    --name spark-pi \
    --class org.apache.spark.examples.SparkPi \
    --conf spark.kubernetes.container.image=apache/spark:3.4.1 \
    --conf spark.kubernetes.authenticate.driver.serviceAccountName=spark \
    --conf spark.kubernetes.driverEnv.SPARK_USER=spark \
    --conf spark.executor.instances=2 \
    local:///opt/spark/examples/jars/spark-examples_2.12-3.4.1.jar
```

```shell
minikube mount /Users/rajveersingh/kubevol/spark-apps:/tmp/spark-apps

minikube ssh
ls -ld /tmp/spark-apps
ls /tmp/spark-apps
```

```shell
./bin/spark-submit \
    --master k8s://https://127.0.0.1:50926 \
    --deploy-mode cluster \
    --name spark-spring \
    --class com.telos.sparkspringcloudtask.SparkSpringCloudTask \
    --conf spark.kubernetes.container.image=officiallysingh/spark:3.4.1 \
    --conf spark.kubernetes.authenticate.driver.serviceAccountName=spark \
    --conf spark.kubernetes.driverEnv.SPARK_USER=spark \
    --conf spark.executor.instances=2 \
    --conf spark.kubernetes.file.upload.path=/tmp/spark-apps \
    --conf spark.kubernetes.driver.volumes.hostPath.spark-host-mount.mount.path=/tmp/spark-apps \
    --conf spark.kubernetes.driver.volumes.hostPath.spark-host-mount.options.path=/tmp/spark-apps \
    --conf spark.kubernetes.executor.volumes.hostPath.spark-host-mount.mount.path=/tmp/spark-apps \
    --conf spark.kubernetes.executor.volumes.hostPath.spark-host-mount.options.path=/tmp/spark-apps \
    local:///tmp/spark-apps/spark-spring-cloud-task.jar
```
Spring batch jobs may require boilerplate code to be written, which is extracted out in this library to promote reusability.
Common components of a Spring batch job are defined as Beans and can be reused across multiple jobs. 
**See usage** in Spring Batch Job implemented as [**`Spring Cloud Task`**](https://github.com/officiallysingh/spring-boot-batch-cloud-task) 
and [**`Spring Rest service`**](https://github.com/officiallysingh/spring-boot-batch-web).

## Features
* Provides common components and utility classes to easily create Spring batch jobs.
* 



> [!IMPORTANT]
Placeholder.

```java
Placeholder
```
