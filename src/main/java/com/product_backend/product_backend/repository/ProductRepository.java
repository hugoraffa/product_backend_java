package com.product_backend.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product_backend.product_backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // TODO add DB evolved queries here

    static public void updateProduct(Product updatingProduct, Product updatedProduct) {

        String code = updatingProduct.getCode();
        if (code != null) {
            updatedProduct.setCode(code);
        }

        String name = updatingProduct.getName();
        if (name != null) {
            updatedProduct.setName(name);
        }

        String description = updatingProduct.getDescription();
        if (description != null) {
            updatedProduct.setDescription(description);
        }

        Float price = updatingProduct.getPrice();
        if (price != null) {
            updatedProduct.setPrice(price);
        }

        Integer quantity = updatingProduct.getQuantity();
        if (quantity != null) {
            updatedProduct.setQuantity(quantity);
        }

        String inventoryStatus = updatingProduct.getInventoryStatus();
        if (inventoryStatus != null) {
            updatedProduct.setInventoryStatus(inventoryStatus);
        }

        String category = updatingProduct.getCategory();
        if (category != null) {
            updatedProduct.setCategory(category);
        }

        String image = updatingProduct.getImage();
        if (image != null) {
            updatedProduct.setImage(image);
        }

        Float rating = updatingProduct.getRating();
        if (rating != null) {
            updatedProduct.setRating(rating);
        }
    }
}
