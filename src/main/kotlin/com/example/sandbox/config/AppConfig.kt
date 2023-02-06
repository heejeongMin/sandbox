package com.example.sandbox.config

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Tag
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CustomMeterRegistry(registry: MeterRegistry) {

    init {
        prometheusExampleCounter = registry.counter(
            "prometheus_example_counter", listOf(Tag.of("location", MetricTag.Example_Tag.name)))
    }

    @Bean
    fun timedAspect(registry: MeterRegistry): TimedAspect {
        return TimedAspect(registry)
    }

    companion object {
        lateinit var prometheusExampleCounter: Counter
    }
}

enum class MetricTag {
    Example_Tag
}