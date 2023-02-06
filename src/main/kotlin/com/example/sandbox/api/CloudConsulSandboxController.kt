package com.example.sandbox.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*
import java.util.function.Function
import java.util.function.Supplier
import javax.naming.ServiceUnavailableException


@RestController
class CloudConsulSandboxController(
    @Value("\${mygreet}") private var greeting: String,
//    private val config : MyProperties
    val discoveryClient: DiscoveryClient? = null
) {

    @GetMapping("/hello")
    fun getHello() = greeting

//    @GetMapping("/greeting")
//    fun getGreeting() = config.greeting

    @GetMapping("/discoveryClient")
    fun discoveryPing(): String? {
        val service: Any = serviceUrl()!!
            .map(Function<Any, Any> { s: Any -> (s as URI).resolve("/ping") })
            .orElseThrow(Supplier { ServiceUnavailableException() })
        return RestTemplate().getForEntity(service as URI, String::class.java).getBody()
    }

    @GetMapping("/ping")
    fun ping(): String? {
        return "pong"
    }

    fun serviceUrl(): Optional<Any>? {
        return discoveryClient!!.getInstances("myApp")
            .stream()
            .findFirst()
            .map<Any> { si: ServiceInstance -> si.uri }
    }

}