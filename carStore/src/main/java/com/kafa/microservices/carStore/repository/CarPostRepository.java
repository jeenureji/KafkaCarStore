package com.kafa.microservices.carStore.repository;

import com.kafa.microservices.carStore.entity.CarPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPostRepository extends JpaRepository<CarPostEntity, Long> {

}
