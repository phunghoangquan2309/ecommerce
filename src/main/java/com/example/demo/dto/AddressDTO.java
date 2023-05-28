package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    @Size(max = 20)
    private String city;

    @Size(max = 20)
    private String district;
    @Size(max = 20)
    @JsonProperty("sub_district")
    private String subDistrict;
}
