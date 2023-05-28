package com.example.demo.entity.ecommerce;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity<Long> {

  public enum Status{
    INACTIVE, ACTIVE
  }

  private String name;

  private String description;

  private String image;
  @Enumerated(value = EnumType.STRING)
  private Status status;


}



