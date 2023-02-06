package com.example.sandbox.api

import com.example.sandbox.config.CustomMeterRegistry
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PrometheusSandboxController {

    @Timed(value = "prometheus.test.time", description = "Time taken to return prometheus")
    @GetMapping("/prometheus/test")
    fun prometheusTest() : ResponseEntity<String> {
        LOGGER.info { "test" }
        CustomMeterRegistry.prometheusExampleCounter.increment()
        return ResponseEntity.ok().body("prometheus!")
    }

    companion object {
        private val LOGGER = KotlinLogging.logger { }
    }
}