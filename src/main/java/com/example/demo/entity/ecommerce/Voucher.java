package com.example.demo.entity.ecommerce;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Voucher extends BaseEntity<Long> {
  public enum Type{
    PERCENT, FIXED_AMOUNT
  }

  public enum Status {
   ACTIVE, EXPIRED, DISABLES
  }
  @Column(name = "code")
  private String code;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private Type couponType;

  @Column(name = "value")
  private String value;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "min_spend")
  private Date minSpend;

  @Column(name = "max_spend")
  private Date maxSpend;

  @Column(name = "uses_per_customer")
  private Integer usesPerCustomer;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;

}
