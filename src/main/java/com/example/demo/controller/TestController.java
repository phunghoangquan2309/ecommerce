package com.example.demo.controller;

import com.example.demo.base.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/a")
    @PreAuthorize("hasAuthority('SELLER')")
    @Transactional
    public ResponseEntity<BaseResponse<String>> signUpForCustomer() {
        try{
            return BaseResponse.generateResponseSuccess("success");
        }
        catch (Exception e) {
            return BaseResponse.generateResponseError(e.getMessage());
        }
    }
}
