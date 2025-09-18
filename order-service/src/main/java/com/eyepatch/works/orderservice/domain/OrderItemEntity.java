package com.eyepatch.works.orderservice.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_items")
class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_item_id_generator")
    @SequenceGenerator(name = "order_item_id_generator",sequenceName = "order_item_id_seq")
    private Long id;

    @Column(nullable = false)
    private String code;

    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public String getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
