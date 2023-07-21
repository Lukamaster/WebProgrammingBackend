package com.webprogramming.backend.web;

import com.webprogramming.backend.model.WebProduct;
import com.webprogramming.backend.service.WebProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:3000")
public class ProductsController {

    private final WebProductService webProductService;

    public ProductsController(WebProductService webProductService) {
        this.webProductService = webProductService;
    }


    @GetMapping("/{categoryName}")
    public ResponseEntity<List<WebProduct>> showProductsByCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok(webProductService.listByCategory(categoryName));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WebProduct>> showAllProducts() {
        return  ResponseEntity.ok(webProductService.listAllProducts());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<WebProduct> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(webProductService.findById(id));
    }

    @GetMapping("/search/{searchQuery}")
    public ResponseEntity<WebProduct> searchProduct(@PathVariable String searchQuery) {
        return ResponseEntity.ok(webProductService.findByName(searchQuery));
    }
}
