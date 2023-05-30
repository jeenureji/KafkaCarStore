package com.kafa.microservices.carStore.message;

import com.kafa.microservices.carStore.dto.CarPostDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-service}")
    private String bootStrapService;

    @Bean
    public ConsumerFactory<String, CarPostDto> consumerFactory (){
        Map<String, Object> carPostDtoMap = new HashMap<>();
        carPostDtoMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapService);
        carPostDtoMap.put(ConsumerConfig.GROUP_ID_CONFIG, "store-post-group");
        carPostDtoMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        carPostDtoMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        carPostDtoMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(carPostDtoMap, new StringDeserializer(),
                new JsonDeserializer<>(CarPostDto.class, false));
    }

    @Bean
 public ConcurrentKafkaListenerContainerFactory<String, CarPostDto> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, CarPostDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
return factory;
    }
}
