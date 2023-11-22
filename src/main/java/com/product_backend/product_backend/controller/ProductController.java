package com.product_backend.product_backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_backend.product_backend.repository.ProductRepository;

import com.product_backend.product_backend.models.Product;

@CrossOrigin(origins = "http://localhost:4567")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    ///////////////////////////////////////////
    // all products methods
    //////////////////////////////////////////

    // Retrieve all products
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {

        Map<String, Object> retObj = new HashMap<>();
        retObj.put("products", productRepository.findAll());

        return new ResponseEntity<>(retObj, HttpStatus.OK);
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {

        if (product.hasId() && productRepository.findById(product.getId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Product savedProduct = productRepository.save(product);

        Map<String, Object> retObj = new HashMap<>();
        retObj.put("id", savedProduct.getId());

        return new ResponseEntity<>(retObj, HttpStatus.CREATED);
    }

    ///////////////////////////////////////////
    // one product method
    ///////////////////////////////////////////

    // Retrieve details for product productId
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update details of product productId if it exists
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> partialUpdateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatingProduct) {

        Optional<Product> existingProduct = productRepository.findById(productId);

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();

            ProductRepository.updateProduct(updatingProduct, updatedProduct);

            Product savedProduct = productRepository.save(updatedProduct);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Remove product productId
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
