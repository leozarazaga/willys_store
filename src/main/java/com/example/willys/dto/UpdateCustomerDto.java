package com.example.willys.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class UpdateCustomerDto {
    private Optional<String> name;
    private Optional<Boolean> willysPlus;
}
