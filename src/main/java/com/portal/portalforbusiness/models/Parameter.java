package com.portal.portalforbusiness.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parameter")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getId() {
        return id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setName(String name) {
        this.name = name;
    }
}
