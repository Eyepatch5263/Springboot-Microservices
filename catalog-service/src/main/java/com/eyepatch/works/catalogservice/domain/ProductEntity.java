package com.eyepatch.works.catalogservice.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="products")
 class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator",sequenceName = "Product_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Product code is required")
    private String code;

    private String description;
    private String name;
    private String imageUrl;

    @NotNull(message = "Product price is required")@DecimalMin("0.1")
    @Column(nullable = false)
    private BigDecimal price;

    public ProductEntity(){}

    public ProductEntity(Long id, String code, String description, String name, String imageUrl, BigDecimal price) {
        this.id=id;
        this.code = code;
        this.description = description;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
