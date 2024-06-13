package com.ksoot.spark;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableTask
@SpringBootApplication(exclude = {GsonAutoConfiguration.class})
public class SparkSpringCloudTask {

  public static void main(String[] args) {
    SpringApplication.run(SparkSpringCloudTask.class, args);
  }

  @PostConstruct
  public void init() {
    log.info("Initialization ...");
  }

  @Bean
  public ApplicationRunner applicationRunner(
      final SparkSession sparkSession, final SparkTaskExecutor sparkTaskExecutor) {
    return new SparkPipelineRunner(sparkSession, sparkTaskExecutor);
  }

  @Slf4j
  public static class SparkPipelineRunner implements ApplicationRunner {

    private final SparkSession sparkSession;

    private final SparkTaskExecutor sparkTaskExecutor;

    public SparkPipelineRunner(final SparkSession sparkSession, final SparkTaskExecutor sparkTaskExecutor) {
      this.sparkSession = sparkSession;
      this.sparkTaskExecutor = sparkTaskExecutor;
    }

    @Override
    public void run(final ApplicationArguments args) {
      this.sparkTaskExecutor.execute(this.sparkSession);
      Dataset<Row> result = this.sparkTaskExecutor.execute(this.sparkSession);
      result.printSchema();
      result.show();
      System.out.println("Result count: " + result.count());
    }
  }
}
