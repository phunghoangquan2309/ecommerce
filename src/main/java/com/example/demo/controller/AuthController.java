package com.example.demo.controller;

import com.example.demo.base.BaseResponse;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.ecommerce.Customer;
import com.example.demo.entity.ecommerce.Seller;
import com.example.demo.entity.security.Role;
import com.example.demo.entity.security.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SellerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.LoginDTO;
import com.example.demo.security.jwt.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/long-process")
    public ResponseEntity<String> startLongProcess() throws InterruptedException {
        // Start the long process
        userRepository.findAll();
        Thread.sleep(30000);
        // Return a response to the client
        return ResponseEntity.accepted().body("Long process started.");
    }

    @PostMapping("/sign-in")
    @Transactional
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWord()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtil.generateToken(authentication);
            LoginResponse loginResponse = new LoginResponse(jwtToken);
            return BaseResponse.generateResponseSuccess(loginResponse);
        }
        catch (Exception e) {
            return BaseResponse.generateResponseError(e.getMessage());
        }
    }

    @PostMapping("/customer/sign-up")
    @Transactional
    public ResponseEntity<BaseResponse<String>>  signUpForCustomer(@RequestBody UserDTO userDTO) {
        try{
            if(userRepository.existsUserByUserName(userDTO.getUserName())){
                throw new Exception("User already exists");
            }
            User user = modelMapper.map(userDTO, User.class);
            user.setRole(Role.newInstanceRoleCustomer());
            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
            Customer customer = new Customer(user);
            customerRepository.save(customer);
            return BaseResponse.generateResponseSuccess("success");
        }
        catch (Exception e) {
            return BaseResponse.generateResponseError(e.getMessage());
        }
    }

    @PostMapping("/seller/sign-up")
    @Transactional
    public ResponseEntity<BaseResponse<String>>  signUpForSeller(@RequestBody UserDTO userDTO) {
        try{
            if(userRepository.existsUserByUserName(userDTO.getUserName())){
                throw new Exception("User already exists");
            }
            User user = modelMapper.map(userDTO, User.class);
            user.setRole(Role.newInstanceRoleSeller());
            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
            Seller seller = new Seller(user);
            sellerRepository.save(seller);
            return BaseResponse.generateResponseSuccess("success");
        }
        catch (Exception e) {
            return BaseResponse.generateResponseError(e.getMessage());
        }
    }

    @PostMapping("/admin/sign-up")
    @Transactional
    public ResponseEntity<BaseResponse<String>>  signUpForAdmin(@RequestBody UserDTO userDTO) {
        try{
            if(userRepository.existsUserByUserName(userDTO.getUserName())){
                throw new Exception("User already exists");
            }
            User user = modelMapper.map(userDTO, User.class);
            user.setRole(Role.newInstanceRoleAdmin());
            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
            return BaseResponse.generateResponseSuccess("success");
        }
        catch (Exception e) {
            return BaseResponse.generateResponseError(e.getMessage());
        }
    }

}
