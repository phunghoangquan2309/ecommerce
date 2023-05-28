package com.example.demo.security;

import com.example.demo.entity.security.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                                  .orElseThrow(() ->  new UsernameNotFoundException("User Not Found with username: " + username));

        UserSecurity userSecurity = new UserSecurity(user);
        return userSecurity;
    }
}
