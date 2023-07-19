package com.webprogramming.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productCode;
    private Integer price;
    @ManyToOne
    private ProductCategory productCategory;
    private String productBrand;
    private String deliveryLocation;
    private String productDescription;
    private String specifications;

    @ManyToMany
    List<ShoppingCart> carts;
}
