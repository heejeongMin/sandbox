# Purpose
Provides auto configuration and binding to Spring environment.

# Features

## Service Discovery
- instances can be registered with Consul agent and clients can discover the instances using Spring managed beans

## Distributed Configuration
- using Consul Key/Value store

## Sample Project For Key/Value store

### Consul Setup

1. Installation : https://developer.hashicorp.com/consul/downloads
    - Start Agent and check if it runs in local
        ```
        $ consul agent -dev
        $ consul members
        ```
    - access to consul ui : http://localhost:8500/ui/dc1/kv

2. add key/value to
    - Option 1) add key/value by command one by one
        ```
        consul kv put <key> <value>
        ```
    - Option 2) Add it from UI either one by one or upload a file key/value > create
    - Option 3) upload key/value json files by command line
        ```
        consul kv import @<filepath>.json
        ```

3. Stop consul
    ```
    consul leave
    ```

### Spring Boot Configuration

1. build.gradle.kt
    ```
    implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
    ```
2. application.properties
    ```
    spring.config.import=optional:consul:
    spring.application.name=sandbox
    spring.cloud.consul.enabled=true
    spring.cloud.consul.config.prefix=pancho
    ```
    ** spring.config.import's value optional:consul: will lookup localhost:8500 which is default consul host/port in local
    ** spring.application.name is going to be the service name in consul and also the path it tries to find the key/value under spring.cloud.consul.config.prefix.
    ** If spring.cloud.consul.config.prefix is not set, it will look up “/config” by default


## Usage within Project

Binding the value to the key in either application.properties or source code with ${key}



Todo

test service discovery
test ACL




# References

https://docs.spring.io/spring-cloud-consul/docs/current/reference/html/#quick-start

https://developer.hashicorp.com/consul/downloads

https://developer.hashicorp.com/consul/tutorials/interactive/get-started-key-value-store

https://www.joinc.co.kr/w/man/12/consul

https://medium.com/nerd-for-tech/a-guide-on-using-consul-as-a-kv-store-for-a-spring-boot-application-de833979da0f .

https://www.baeldung.com/spring-cloud-consul