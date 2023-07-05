package com.webprogramming.backend.repository;

import com.webprogramming.backend.model.ProductCategory;
import com.webprogramming.backend.model.WebProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebProductRepository extends JpaRepository<WebProduct, Long> {

    List<WebProduct> findWebProductsByProductCategory(ProductCategory productCategory);

    WebProduct findWebProductByProductName(String name);

    List<WebProduct> findWebProductsByProductBrand(String brand);
}
