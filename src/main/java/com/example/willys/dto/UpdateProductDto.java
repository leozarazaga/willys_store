package com.example.willys.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class UpdateProductDto {
    private Optional<String> name;
    private Optional<Double> price;
    private Optional<String> description;
}
