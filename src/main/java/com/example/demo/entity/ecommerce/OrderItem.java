package com.example.demo.entity.ecommerce;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem extends BaseEntity<Long> {

  private Long quantity;

  @Column(name = "total_price_item")
  private Long totalPriceItem;

}
