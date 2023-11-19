package com.example.willys.controller;

import com.example.willys.dto.CreateProductDto;
import com.example.willys.dto.UpdateProductDto;
import com.example.willys.entity.Product;
import com.example.willys.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*    - - - - - - - - - - - - - - - - - - -   */

    @PostMapping("/product/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        Product product = productService.createProduct(createProductDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product/search/{search}")
    public ResponseEntity<List<Product>> searchByNameAndDescription(@PathVariable String search) {
        List<Product> products = productService.searchByNameAndDescription(search);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @RequestBody UpdateProductDto updateProductDto) {
        Product product = productService.updateProduct(id, updateProductDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
