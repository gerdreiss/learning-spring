package com.github.gerdreiss;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class DummyMetricsConfiguration {

    @Bean
    public Counter dummyCallCounter(MeterRegistry registry) {
        return Counter
                .builder("api.dummy.created")
                .description("Count of dummy calls")
                .register(registry);
    }

    @Bean
    public Timer createdDummyTimer(MeterRegistry registry) {
        return Timer
                .builder("api.dummy.returned.time")
                .description("Time taken to return a dummy")
                .sla(Duration.ofMillis(10))
                .minimumExpectedValue(Duration.ofMillis(1))
                .maximumExpectedValue(Duration.ofSeconds(2))
                .publishPercentiles(0.5, 0.95)
                .publishPercentileHistogram()
                .register(registry);
    }

    @Bean
    public Gauge countProfilesGauge(MeterRegistry registry) {
        return Gauge
                .builder("api.dummy.count", () -> 1)
                .description("Amount of existing dummies")
                .register(registry);
    }

    @Bean
    public InMemoryHttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}