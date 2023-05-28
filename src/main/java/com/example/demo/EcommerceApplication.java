package com.example.demo;

import com.example.demo.entity.security.Role;
import com.example.demo.entity.security.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role role1 = Role.newInstance(Role.ROLE.CUSTOMER);
		User user = User.builder()
				.userName("quan")
				.passWord(passwordEncoder.encode("hoang"))
				.email("phunghoangquan2309@gmail.com")
				.role(role1)
				.build();
		userRepository.save(user);
		Role roles2 = Role.newInstance(Role.ROLE.SELLER);
		User user1 = User.builder()
				.userName("quan1")
				.passWord(passwordEncoder.encode("hoang1"))
				.email("phunghoangquan1@gmail.com")
				.role(roles2)
				.build();
		userRepository.save(user1);
//		LoginDTO loginRequest = new LoginDTO();
//		loginRequest.setUserName("quan");
//		loginRequest.setPassWord("hoang");
//		Customer customer = new Customer(user1);
//		customerRepository.save(customer);
//		Customer customer2 = new Customer(user);
//		customerRepository.save(customer2);
	}
}
