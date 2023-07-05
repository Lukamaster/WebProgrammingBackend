package com.webprogramming.backend.service;

import com.webprogramming.backend.model.WebProduct;

import java.util.List;

public interface WebProductService {

    List<WebProduct> listAllProducts();

    List<WebProduct> listByCategory(String category);

    List<WebProduct> listByBrand(String brand);

    WebProduct findById(Long id);
    WebProduct findByName(String name);

    WebProduct createProduct();

    WebProduct updateProduct();

    WebProduct deleteProduct(Long id);

}
