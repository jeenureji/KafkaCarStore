package com.kafa.microservices.carStore.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name= "car_post")
@Data
@NoArgsConstructor
public class CarPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "car_model")
    private String model;

    @Column(name = "car_brand")
    private String brand;

    @Column(name = "car_price")
    private Double price;

    @Column(name = "car_description")
    private String description;

    @Column(name = "engine_version")
    private String engineVersion;

    @Column(name="city")
    private String city;

    @Column(name="createDate")
    private String createDate;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "owner_post_id", nullable = false, referencedColumnName = "id")
    private OwnerPostEntity ownerPost;
}
