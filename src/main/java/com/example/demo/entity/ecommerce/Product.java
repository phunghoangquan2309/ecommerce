package com.example.demo.entity.ecommerce;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity<Long> {
  @AllArgsConstructor
  public enum Status {
    DANG_BAN("đang bán"), NGUNG_KINH_DOANH("ngừng kinh doanh");
    private final String label;

    static public Status[] ALL = Status.values();

  }

  @Column(name = "category_id")
  private Long categoryId;

  private String name;

  private String description;

  private Float price;

  private Float discount;

  private Integer quantity;

  private Integer sold;

  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToMany(cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
  })
  @JoinTable(
          name = "product_category",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private List<Category> categories = new ArrayList<>();

  public void addCategory(Category category) {
    categories.add(category);
  }

}
