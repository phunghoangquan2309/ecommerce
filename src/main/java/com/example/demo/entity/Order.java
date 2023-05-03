package com.example.demo.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity<Long>{
  @AllArgsConstructor
  public enum Status{
    DANG_CHO_XU_LY("đang chờ xử lý"), DA_XU_LY("đã xử lý"),
    DA_GIAO_HANG("đã giao hàng"), HUY_DON_HANG("hủy đơn hàng");
    private final String value;
  }

  private Double shipping_fee;

  private Double total;
  @Column(name = "canceled_time")
  private Date canceledTime;

  @Column(name = "completed_time")
  private Date completedTime;

  @Column(name = "delivery_time")
  private Date deliveryTime;

}
