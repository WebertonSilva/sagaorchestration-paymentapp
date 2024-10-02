package com.saga.payments.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Duration;


@Configuration
@EntityScan(basePackages = "com.saga")
@EnableJpaRepositories(basePackages = "com.saga")
public class CircuitBreakerConfiguration {

        @Bean
        public CircuitBreaker customCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry){
            CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                    .failureRateThreshold(50)//limite da taxa de falha em porcentagem
                    .permittedNumberOfCallsInHalfOpenState(3) //NúmeroPermitidoDeChamadasEmEstadoMeioAberto
                    .slidingWindowSize(1000) //Configura o tamanho da janela deslizante que é usada para registrar o resultado das chamadas quando o CircuitBreaker é fechado.
                    .waitDurationInOpenState(Duration.ofMillis(6000)) // O tempo que o disjuntor deve esperar antes de passar de aberto para semiaberto.
                    .permittedNumberOfCallsInHalfOpenState(1) //NúmeroPermitidoDeChamadasEmEstadoMeioAberto
                    .minimumNumberOfCalls(5) // Configures the minimum number of calls which are required (per sliding window period) before the CircuitBreaker can calculate the error rate or slow call rate.
//                    .recordExceptions(SQLException.class)
                    .build();

            return circuitBreakerRegistry.circuitBreaker("PaymentServices",circuitBreakerConfig);
        }

        @Bean
        public CircuitBreakerRegistry CircuitBreakerRegistry(){
            return CircuitBreakerRegistry.ofDefaults();
        }
}

