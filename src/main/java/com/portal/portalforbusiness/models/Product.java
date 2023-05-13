package com.portal.portalforbusiness.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Currency;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Float price;
    private Currency currency;
    private ProductType type;
    private String description;
    @Column(name = "image")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Currency getCurrencyType() {
        return currency;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCurrency(String currency) {
        this.currency = Currency.getInstance(currency);
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
