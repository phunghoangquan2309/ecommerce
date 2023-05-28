package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    @JsonProperty("username")
    @Size(max = 30)
    private String userName;

    @NotBlank
    @JsonProperty("password")
    @Size(max = 30)
    private String passWord;
}
