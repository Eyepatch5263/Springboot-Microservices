package com.eyepatch.works.orderservice.domain;

import com.eyepatch.works.orderservice.domain.models.CreateOrderRequest;
import com.eyepatch.works.orderservice.domain.models.OrderItem;
import com.eyepatch.works.orderservice.domain.models.OrderStatus;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class OrderMapper {
    static OrderEntity convertToEntity(CreateOrderRequest request) {
        OrderEntity newOrder = new OrderEntity();
        newOrder.setOrderNumber(UUID.randomUUID().toString());
        newOrder.setCustomer(request.customer());
        newOrder.setStatus(OrderStatus.NEW);
        newOrder.setDeliveryAddress(request.deliveryAddress());
        Set<OrderItemEntity> orderItems = new HashSet<>();
        for(OrderItem item: request.items()){
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setCode(item.code());
            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(item.price());
            orderItem.setName(item.name());
            orderItem.setOrder(newOrder);
            orderItems.add(orderItem);
        }
        newOrder.setItems(orderItems);
        return newOrder;

    }
}
