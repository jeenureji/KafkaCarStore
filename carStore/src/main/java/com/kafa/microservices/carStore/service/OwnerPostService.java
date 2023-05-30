package com.kafa.microservices.carStore.service;

import com.kafa.microservices.carStore.dto.OwnerPostDto;
import com.kafa.microservices.carStore.entity.OwnerPostEntity;
import com.kafa.microservices.carStore.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerPostService {

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    public void createOwnerPost(OwnerPostDto ownerPostDto){
        OwnerPostEntity ownerPost = new OwnerPostEntity();
        ownerPost.setContactNumber(ownerPostDto.getContactNumber());
        ownerPost.setName(ownerPostDto.getName());
        ownerPost.setType(ownerPostDto.getType());
        ownerPostRepository.save(ownerPost);
    }

}
