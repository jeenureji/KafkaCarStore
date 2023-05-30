package com.kafa.microservices.carStore.service;

import com.kafa.microservices.carStore.dto.CarPostDto;
import com.kafa.microservices.carStore.entity.CarPostEntity;
import com.kafa.microservices.carStore.repository.CarPostRepository;
import com.kafa.microservices.carStore.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarPostService {

    @Autowired
    private CarPostRepository carPostRepository;

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    public List<CarPostDto> getCarSales () {
        List<CarPostDto> carPostDtos = new ArrayList<>();
        carPostRepository.findAll().forEach(a -> {
            carPostDtos.add(mapEntityToDto(a));
        });
               return carPostDtos;
    }
    public void changeCarSale(CarPostDto carPostDto, Long id){
        carPostRepository.findById(id).ifPresentOrElse(item -> {
            CarPostEntity carPostEntity = new CarPostEntity();
            carPostEntity.setDescription(carPostDto.getDescription());
            carPostEntity.setContact(carPostDto.getContact());
        }, () -> { throw new RuntimeException();});
    }
    public void deleteCarSale(Long id){
        carPostRepository.deleteById(id);
    }

    public void newPostDetails(CarPostDto carPostDto) {
        CarPostEntity carPostEntity = mapCarDtoToEntity(carPostDto);
        carPostRepository.save(carPostEntity);
    }

    public CarPostEntity mapCarDtoToEntity(CarPostDto carPostDto) {
        CarPostEntity carPostEntity = new CarPostEntity();
        ownerPostRepository.findById(carPostDto.getOwnerId()).ifPresentOrElse(a -> {
            carPostEntity.setOwnerPost(a);
            carPostEntity.setContact(a.getContactNumber());
        }, () -> {
            throw new RuntimeException();

        });
        carPostEntity.setBrand(carPostDto.getBrand());
        carPostEntity.setCity(carPostDto.getCity());
        carPostEntity.setDescription(carPostDto.getDescription());
        carPostEntity.setEngineVersion(carPostDto.getEngineVersion());
        carPostEntity.setCreateDate(String.valueOf(new Date()));
        carPostEntity.setModel(carPostDto.getModel());
        carPostEntity.setPrice(carPostDto.getPrice());
        return carPostEntity;

    }
    public CarPostDto mapEntityToDto(CarPostEntity carPostEntity){
        return CarPostDto.builder()
                .brand(carPostEntity.getBrand())
                .city(carPostEntity.getCity())
                .contact(carPostEntity.getContact())
                .createDate(carPostEntity.getCreateDate())
                .description(carPostEntity.getDescription())
                .engineVersion(carPostEntity.getEngineVersion())
                .model(carPostEntity.getModel())
                .ownerId(carPostEntity.getOwnerPost().getId())
                .ownerName(carPostEntity.getOwnerPost().getName())
                .ownerType(carPostEntity.getOwnerPost().getType())
                .price(carPostEntity.getPrice())
                .build();

    }
}
