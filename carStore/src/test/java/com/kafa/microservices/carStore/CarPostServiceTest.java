package com.kafa.microservices.carStore;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafa.microservices.carStore.dto.CarPostDto;
import com.kafa.microservices.carStore.entity.CarPostEntity;
import com.kafa.microservices.carStore.entity.OwnerPostEntity;
import com.kafa.microservices.carStore.repository.CarPostRepository;
import com.kafa.microservices.carStore.repository.OwnerPostRepository;
import com.kafa.microservices.carStore.service.CarPostService;
import jdk.jfr.Experimental;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.ManyToOne;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CarPostServiceTest {



    @Mock
    CarPostRepository carPostRepository;

    @Mock
    OwnerPostRepository ownerPostRepository;

    @InjectMocks
    CarPostService carPostService;

    public static CarPostDto getCarPost() throws IOException{
        File file = new File("src/test/resources/carPost.json");
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//        TypeReference<List<CarPostDto>> typeReference = new TypeReference<>() {
//        };
        return objectMapper.readValue(file,CarPostDto.class);
    }
    public OwnerPostEntity getCarOwnerPost() throws IOException{
        File file = new File("src/test/resources/ownerPost.json");
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//        TypeReference<List<CarPostDto>> typeReference = new TypeReference<>() {
//        };
        return objectMapper.readValue(file,OwnerPostEntity.class);
    }

    @Test
    public void testCarPost() throws IOException{
    CarPostDto carPostDto = new CarPostDto();
    OwnerPostEntity ownerPostEntity = getCarOwnerPost();
    given(ownerPostRepository.findById(ownerPostEntity.getId())).willReturn(Optional.of(ownerPostEntity));
    CarPostEntity carPostEntity1 = carPostService.mapCarDtoToEntity(getCarDetails());
    assertThat(carPostEntity1).isNotNull();
    }
    public static CarPostDto getCarDetails() throws IOException {
        CarPostDto carPostDto = getCarPost();
        if (Objects.equals(carPostDto.getModel(), "thar")) {
            log.info("able to fetch car post details : " + carPostDto);
        }
            return carPostDto;
    }


}
