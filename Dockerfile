# Use an existing image as the base image
ARG java_image_tag=17-jre
FROM eclipse-temurin:${java_image_tag}

# Use the Spark base image
#FROM officiallysingh/spark:3.4.1

EXPOSE 8090

# Set the working directory
WORKDIR /opt/spark/work-dir

# Copy the compiled JAR files to the image
COPY spark-data-fetcher-driver/target/spark-spring-cloud-task-0.0.1-SNAPSHOT.jar /opt/spark/work-dir/spark-spring-cloud-task.jar.jar
COPY spark-data-fetcher-executor/target/spark-data-fetcher-executor-0.0.1-SNAPSHOT.jar /opt/spark/work-dir/spark-data-fetcher-executor.jar

# Set environment variable for JVM arguments
ENV JAVA_OPTS="--add-exports java.base/sun.nio.ch=ALL-UNNAMED"

# Set the entrypoint command to run the JAR file
#ENTRYPOINT ["java", "--add-exports", "java.base/sun.nio.ch=ALL-UNNAMED", "-jar", "spark-data-fetcher-driver.jar"]
CMD ["sh", "-c", "java $JAVA_OPTS -jar spark-data-fetcher-driver.jar"]