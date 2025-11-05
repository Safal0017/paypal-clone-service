package com.paypal.notification_service.config;

import com.paypal.notification_service.entity.Transaction;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, Transaction> consumerFactory() {
        JsonDeserializer<Transaction> deserializer = new JsonDeserializer<>(Transaction.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.setUseTypeMapperForKey(true);
        deserializer.addTrustedPackages("com.paypal.transaction_service.entity");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Transaction> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Transaction> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}


//In short easy terms:
//Your KafkaConsumerConfig works because it tells Spring Kafka exactly
// how to convert JSON message bytes into your Transaction Java object,
// including allowing the deserializer to “trust” and load the Transaction
// class from the producer’s package, which is why the previous errors (class not found, untrusted package) disappear.
//
//Best practice takeaway:
//When you have multiple services with different class packages for
// shared message types (like your Transaction), programmatic consumer
// config with explicit deserializer setup and trusted packages is the
// most reliable way to make your Kafka listener work smoothly without
// forcing class copies or massive refactoring.
//
//This precise consumer-side deserializer setup is the recommended approach
// in Spring Kafka for complex microservice messaging.