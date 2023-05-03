package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
abstract public class BaseEntity<PK> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected PK id;

  @CreatedDate
  @Column(name = "created_time")
  private Date createdTime;

  @LastModifiedDate
  @Column(name = "last_modified_time")
  private Date lastModifiedTime;

}
