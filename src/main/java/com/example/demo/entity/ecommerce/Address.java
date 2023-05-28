package com.example.demo.entity.ecommerce;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Column(length = 20)
    @Size(max = 20)
    private String city;

    @Column(length = 20)
    @Size(max = 20)
    private String district;
    @Column(name = "sub_district", length = 20)
    @Size(max = 20)
    private String subDistrict;
}
