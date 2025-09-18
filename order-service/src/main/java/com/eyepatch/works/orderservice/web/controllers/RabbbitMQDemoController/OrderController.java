package com.eyepatch.works.orderservice.web.controllers.RabbbitMQDemoController;

import com.eyepatch.works.orderservice.domain.OrderService;
import com.eyepatch.works.orderservice.domain.SecurityService;
import com.eyepatch.works.orderservice.domain.models.CreateOrderRequest;
import com.eyepatch.works.orderservice.domain.models.CreateOrderResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request){
        String username = securityService.getLoginUsername();
        log.info("Received request to create order for user {}", username);
        return orderService.createOrder(username, request);
    }


}
