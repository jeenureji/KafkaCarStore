package com.kafa.microservices.carStore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude
public class OwnerPostDto {
    private String name;

    private String type;

    private String contactNumber;
}
