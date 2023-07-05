package com.webprogramming.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String productName;
    private String productCode;
    private int price;
    @ManyToOne
    private ProductCategory productCategory;
    private String productBrand;
    private String deliveryLocation;
    private String productDescription;
    private String specifications;



}
