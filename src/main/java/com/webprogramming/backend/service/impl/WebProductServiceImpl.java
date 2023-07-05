package com.webprogramming.backend.service.impl;

import com.webprogramming.backend.model.ProductCategory;
import com.webprogramming.backend.model.WebProduct;
import com.webprogramming.backend.repository.CategoryRepository;
import com.webprogramming.backend.repository.WebProductRepository;
import com.webprogramming.backend.service.WebProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebProductServiceImpl implements WebProductService {

    private final WebProductRepository webProductRepository;
    private final CategoryRepository categoryRepository;

    public WebProductServiceImpl(WebProductRepository webProductRepository, CategoryRepository categoryRepository) {
        this.webProductRepository = webProductRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<WebProduct> listAllProducts() {
        return webProductRepository.findAll();
    }

    @Override
    public List<WebProduct> listByCategory(String category) {
        ProductCategory productCategory = categoryRepository.findByCategoryName(category);
        return webProductRepository.findWebProductsByProductCategory(productCategory);
    }

    @Override
    public List<WebProduct> listByBrand(String brand) {
        return webProductRepository.findWebProductsByProductBrand(brand);
    }

    @Override
    public WebProduct findById(Long id) {
        return webProductRepository.findById(id).orElseThrow();
    }

    @Override
    public WebProduct findByName(String name) {
        return webProductRepository.findWebProductByProductName(name);
    }

    @Override
    public WebProduct createProduct() {
        return null;
    }

    @Override
    public WebProduct updateProduct() {
        return null;
    }

    @Override
    public WebProduct deleteProduct(Long id) {
        WebProduct webProduct = this.findById(id);
        this.webProductRepository.delete(webProduct);
        return webProduct;
    }
}
