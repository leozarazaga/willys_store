package com.example.willys.service;

import com.example.willys.dto.CreateProductDto;
import com.example.willys.dto.UpdateProductDto;
import com.example.willys.entity.Product;
import com.example.willys.exception.NoSearchResultFoundException;
import com.example.willys.exception.ProductNotFoundException;
import com.example.willys.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*    - - - - - - - - - - - - - - - - - - -   */

    public Product createProduct(CreateProductDto dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getDescription());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        UUID uuid = UUID.fromString(id);
        return productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> searchByNameAndDescription(String search) {
        List<Product> findByName = productRepository.findByNameContainingIgnoreCase(search);
        List<Product> findByDescription = productRepository.findByDescriptionContainingIgnoreCase(search);

        List<Product> combinedProducts = Stream.concat(findByName.stream(), findByDescription.stream())
                .distinct()
                .collect(Collectors.toList());

        if (combinedProducts.isEmpty()) {
            throw new NoSearchResultFoundException(search);
        }
        return combinedProducts;
    }

    public Product updateProduct(String id, UpdateProductDto dto) {
        UUID uuid = UUID.fromString(id);
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));

        if (dto.getName().isPresent()) {
            product.setName(dto.getName().get());
        }

        if (dto.getPrice().isPresent()) {
            product.setPrice(dto.getPrice().get());
        }

        if (dto.getDescription().isPresent()) {
            product.setDescription(dto.getDescription().get());
        }

        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        UUID uuid = UUID.fromString(id);
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }

}
