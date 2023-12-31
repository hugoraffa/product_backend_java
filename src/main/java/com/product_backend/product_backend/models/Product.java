package com.product_backend.product_backend.models;

import java.util.Objects;
import java.util.Random;

import javax.validation.constraints.NotNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    // generate id if not provided
    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(this.id)) {
            this.id = Math.abs(new Random().nextLong());
        }
    }

    @Column(name = "code")
    @NotBlank
    @NotNull
    private String code;

    @Column(name = "name")
    @NotBlank
    @NotNull
    private String name;

    @Column(name = "description")
    @NotBlank
    @NotNull
    private String description;

    @Column(name = "price")
    @NotNull
    private Float price;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "inventoryStatus")
    @NotBlank
    @NotNull
    private String inventoryStatus;

    @Column(name = "category")
    @NotBlank
    @NotNull
    private String category;

    @Column(name = "image")
    private String image;

    @Column(name = "rating")
    private Float rating;

    public Boolean hasId() {
        return this.id != null;
    }

    // TODO toString
}
