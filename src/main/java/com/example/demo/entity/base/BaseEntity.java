package com.example.demo.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
abstract public class BaseEntity<PK extends Serializable> extends Persistable<PK> {

  private static final long serialVersionUID = 1L;


  @CreatedDate
  @Column(name = "created_time")
  private Date createdTime;

  @LastModifiedDate
  @Column(name = "last_modified_time")
  private Date lastModifiedTime;

}
