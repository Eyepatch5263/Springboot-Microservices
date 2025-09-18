package com.eyepatch.works.orderservice.domain;


import com.eyepatch.works.orderservice.domain.models.Address;
import com.eyepatch.works.orderservice.domain.models.Customer;
import com.eyepatch.works.orderservice.domain.models.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "order_id_generator")
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq")
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false, name = "username")
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItemEntity> items;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "name", column = @Column(name="customer_name")),
                    @AttributeOverride(name="email",column = @Column(name="customer_email")),
                    @AttributeOverride(name="phone",column = @Column(name = "customer_phone"))
            })
    private Customer  customer;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "addressLine1", column = @Column(name="delivery_address_line1")),
                    @AttributeOverride(name="addressLine2",column = @Column(name="delivery_address_line2")),
                    @AttributeOverride(name="city",column = @Column(name = "delivery_address_city")),
                    @AttributeOverride(name="state",column = @Column(name="delivery_address_state")),
                    @AttributeOverride(name="zipcode",column = @Column(name="delivery_address_zipcode")),
                    @AttributeOverride(name="country",column = @Column(name="delivery_address_country"))
})
    private Address deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String comments;

    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(Set<OrderItemEntity> items) {
        this.items = items;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public Long getId() {
        return id;
    }

    public Set<OrderItemEntity> getItems() {
        return items;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getUsername() {
        return username;
    }
}
