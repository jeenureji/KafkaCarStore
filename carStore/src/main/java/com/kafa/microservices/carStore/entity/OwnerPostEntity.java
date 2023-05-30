package com.kafa.microservices.carStore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "owner_post")
@Data
@NoArgsConstructor
public class OwnerPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("ownerId")
    private Long id;

    @JsonProperty("ownerName")
    @Column(name= "owner_name")
    private String name;

    @JsonProperty("ownerType")
    @Column(name= "type")
    private String type;

    @JsonProperty("contact")
    @Column(name = "owner_contactNumber")
    private String contactNumber;
}
