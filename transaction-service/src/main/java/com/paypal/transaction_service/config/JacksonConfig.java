package com.paypal.transaction_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}

//By default, Jackson may serialize LocalDateTime as a
// timestamp (number of milliseconds or similar), which is often not what REST clients expect.

//Registering JavaTimeModule enables Jackson to properly understand and
// convert these date/time types to readable JSON strings like "2025-11-04T20:30:00".

//this config ensures your Spring Boot app serializes/deserializes Java 8
// LocalDateTime and related types according to ISO-8601 string format
// instead of timestamps, which is essential for correct JSON API behavior
// with modern date/time.