package com.example.Order.controller;


import com.example.Order.model.entity.Order;
import com.example.Order.model.entity.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.web.bind.annotation.*;
import com.example.Order.services.OrderServices;

import java.util.List;

@RestController
@RequestMapping("/order")
public class HttpMethodController {

    @Autowired
    OrderServices orderServices;

    @GetMapping("/vieworders")
    public List<Order> getorder()
    {
        return orderServices.getOrderDetails();
    }

    @GetMapping("/findorderhistory")
    public List<Order> viewOrderHistory(String orderId)
    {
        return orderServices.getOrderHistory(orderId);
    }

    @PostMapping(value="/orderproduct")
    public String addDetails(@RequestBody Order order)
    {
        System.out.println("in");
        System.out.println(order.getProductQuantity());
        return orderServices.addOrderDetails(order);
    }
}
