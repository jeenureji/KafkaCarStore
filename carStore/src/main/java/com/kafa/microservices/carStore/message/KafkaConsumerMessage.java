package com.kafa.microservices.carStore.message;

import com.kafa.microservices.carStore.dto.CarPostDto;
import com.kafa.microservices.carStore.service.CarPostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {
    private final Logger log = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
   private CarPostService carPostService;

    @KafkaListener(topics = "car-post-topic", groupId = "store-post-group")
    public void listening(CarPostDto carPostDto){
    log.info("received car post information : {}", carPostDto);
        carPostService.newPostDetails(carPostDto);
    }

}
