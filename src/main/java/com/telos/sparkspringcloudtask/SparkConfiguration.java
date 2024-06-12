package com.telos.sparkspringcloudtask;

import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class SparkConfiguration {

  @Bean(name = "sparkSession", destroyMethod = "stop")
  SparkSession sparkSession() {
    return SparkSession.builder()
        .appName("spark-spring-cloud-task")
        .master("k8s://https://127.0.0.1:51932")
        //                .config("spark.kubernetes.driverEnv.SPARK_MASTER_URL",
        // "spark://telos-spark-master-0.telos-spark-headless.default.svc.cluster.local:7077")
//        .config("spark.kubernetes.driverEnv.SPARK_MASTER_URL", "spark://10.244.0.6:7077")
            .config("spark.kubernetes.driverEnv.SPARK_USER", "spark")
//                        .config("spark.kubernetes.container.image", "apache/spark:3.4.1")
        //            .config("spark.driver.host", hostIp)
        //        .config("spark.driver.port", "8091")
//        .config("spark.kubernetes.namespace", "default")
        .config("spark.kubernetes.authenticate.driver.serviceAccountName", "spark")
//        .config("spark.kubernetes.authenticate.executor.serviceAccountName", "spark")
        .getOrCreate();
  }

  //            .set("spark.driver.host", hostIp)
  //        .set("spark.driver.port", "8091")
  //        .set("spark.kubernetes.namespace", "default")
  //        .set("spark.kubernetes.container.image", "bitnami/spark:3.4.1")
  //        .set("spark.kubernetes.authenticate.executor.serviceAccountName", "spark")
  //        .set("spark.executor.memory", "1g")
  //        .set("spark.executor.instances", "1")
}
