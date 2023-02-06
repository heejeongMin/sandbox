# Purpose
Monitor Application Performance

## Prometheus

Gathers and stores metrics data in a time series format

## Grafana

uses Prometheus as a data source to visualize the data on dashboards.


# How to supply custom metrics from spring to prometheus
 
1. build.gradle.kts

```kotlin
implementation("io.micrometer:micrometer-registry-prometheus:1.9.3")
```

2. application.properties

```kotlin
management.endpoints.web.exposure.include=health,info,prometheus
```

3. call prometheus end point to see what kind of metrics are collected by defaulthttp://localhost:8080/actuator/prometheus

![spring_prometheus_grafana_default_metrics.png](images%2Fspring_prometheus_grafana_default_metrics.png)

- format : metric_name{tagName=tagValue} metricValue
- Types of metric 
  1. Gauge 
     - Measuring resource usage, capacity, etc. 
     - Values that can rise and fall and that have fixed upper bounds. 
       - size of collection 
       - number of running threads 
       - number of messages on a queue 
       - memory usage etc. 
  2. Counter 
     - Measuring a number of events or actions. 
     - A value that only increase, and ever decrease. 
       - total number of orders processed 
       - total tasks completed etc 
  3. Timer 
      - Measuring short-duration events and their frequency 
        - Method execution time 
        - request duration

4. Example on Timer 
- “@Timed” annotation is provided from micrometer to time a response time.
  - how to use it 
    1. build.gradle.kts 
    ``` kotlin
    implementation("org.springframework:spring-aop")
    implementation("org.springframework:spring-aspects")
     ```
    2. Register TimeAspect from Micrometer as a bean
     ```kotlin
     @Bean
      fun timedAspect(registry: MeterRegistry): TimesAspect {
         return TimedAspect(registry)
      }
     ```

   3. Add “@Timed” annotation on the api you want to measure
     ```kotlin
     @Timed(value = "prometheus.test.time", description = "Time taken to return prometheus")
     @GetMapping("/prometheus/test")
     fun prometheusTest() : ResponseEntity<String> {
         LOGGER.info { "test" }
         return ResponseEntity.ok().body("prometheus!")
     } 
   ```
  4. http://localhost:8080/actuator/prometheus
  ![spring_prometheus_grafana_custom_timer.png](images%2Fspring_prometheus_grafana_custom_timer.png)
  only be able to see the metrics when the first call is made
  

5. Example on Counter
- how to use it 
  1. Register Counter 
  ```kotlin
    @Configuration
    class CustomMeterRegistry(registry: MeterRegistry) {

      init {
          prometheusExampleCounter = registry.counter(
              "prometheus_example_counter", listOf(Tag.of("location", MetricTag.Example_Tag.name)))
      }

      companion object {
          lateinit var prometheusExampleCounter: Counter
      }
    }

   enum class MetricTag {
        Example_Tag
   }
    ```

    2. increment counter by calling increment
    ```kotlin
    CustomMeterRegistry.prometheusExampleCounter.increment()
   ```

    3.http://localhost:8080/actuator/prometheus
    ![spring_prometheus_grafana_custom_counter.png](images%2Fspring_prometheus_grafana_custom_counter.png)


# How Prometheus to consume the metrics

1. create config file
```properties
global:
scrape_interval:     15s # Default scrape interval

scrape_configs:
- job_name: 'prometheus' # Job to scrape Prometheus metrics
  scrape_interval: 5s
  static_configs:
    - targets: ['localhost:9090']

- job_name: 'spring-actuator'
  metrics_path: '/actuator/prometheus' # Job to scrape application metrics
  scrape_interval: 5s
  static_configs:
    - targets: ['host.docker.internal:8080']
```

2. run docker
```
docker run -d -p 9090:9090 -v \<absolute_path_to_your_prometheus_file>:/etc/prometheus/prometheus.yml prom/prometheus
```

3. run localhost:9090 to see the UI and able to see the metrics by typing the metric name
![spring_prometheus_grafana_ui.png](images%2Fspring_prometheus_grafana_ui.png)

- Navigate to Status > Targets to see if its all connected.
![spring_prometheus_grafana.png](images%2Fspring_prometheus_grafana.png)

# How to visualize with Grafana

1. run docker
```
docker run -d -p 3000:3000 grafana/grafana
```

2. access to local grafana
- http://localhost:3000/login
- if you don’t set the credential, admin/admin is default

3. configure datasource 
- in order to have grafana to look up prometheus as datasource
- navigate Configuration > Data Sources > Add Data source > select Prometheusand configure the URL and click “save & test” in the bottom of the page
  - prometheus is run in docker, can have the address by running following command
    ```
    docker inspect [container name or container id] | grep IPAddress
    ```
    ![spring_prometheus_grafana_ui_datasources.png](images%2Fspring_prometheus_grafana_ui_datasources.png)
  
4. import dashboard / add custom pannel
- navigate dashboard > import > search 4701 in order to have a preconfigured dashboard > click load
![spring_prometheus_grafana_dashboard_import.png](images%2Fspring_prometheus_grafana_dashboard_import.png)
- choose prometheus and click import
![spring_prometheus_grafana_dashboard_datasource_2.png](images%2Fspring_prometheus_grafana_dashboard_datasource_2.png)
- custom panel can be added by clicking "add panel" and search for the metric name you want 
![spring_prometheus_grafana_dashboard_3.png](images%2Fspring_prometheus_grafana_dashboard_3.png)
- 

# References

https://medium.com/javarevisited/springboot-app-monitoring-with-grafana-prometheus-7c723f0dec15

https://www.tutorialworks.com/spring-boot-prometheus-micrometer/

https://www.tutorialworks.com/container-networking/