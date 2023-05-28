package com.example.demo.entity.security;

import com.example.demo.entity.ecommerce.Address;
import com.example.demo.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(
        name = "user_account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
public class User extends BaseEntity<Long> {

    @NotBlank
    @Column(name = "username" , nullable = false, length = 30)
    @Size(max = 30)
    private String userName;

    @NotBlank
    @Column(name = "password", nullable = false , length = 30)
    @Size(max = 100)
    @JsonIgnore
    private String passWord;

    @Column(name = "full_name" , length = 30)
    @Size(max = 15)
    private String fullName;

    @Column(name = "phone_number", length = 15)
    @Size(max = 15)
    private String phoneNumber;

    @Embedded
    private Address address;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role ;

    @Column(nullable = false, length = 30)
    @Size(max = 30)
    @NotBlank
    @Email
    private String email;
}
