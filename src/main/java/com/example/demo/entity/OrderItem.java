package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class OrderItem extends BaseEntity<Long>{

  private Long quantity;

  @Column(name = "total_price_item")
  private Long totalPriceItem;

}
