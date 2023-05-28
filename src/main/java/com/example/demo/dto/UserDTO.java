package com.example.demo.dto;

import com.example.demo.entity.ecommerce.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    @NotBlank
    @Size(max = 30)
    @JsonProperty("username")
    private String userName;

    @NotBlank
    @Size(max = 100)
    @JsonProperty("password")
    private String passWord;


    @Size(max = 15)
    @JsonProperty("full_name")
    private String fullName;

    @Size(max = 15)
    @JsonProperty("phone_number")
    private String phoneNumber;

    private AddressDTO address;

    @Size(max = 30)
    @NotBlank
    @Email
    private String email;
}


