package com.example.Order.services;

import com.example.Order.model.entity.Order;
import com.example.Order.model.entity.OrderVo;

import java.util.List;

public interface OrderServices {


    List<Order> getOrderDetails();

    String addOrderDetails(Order order);

    List<Order> getOrderHistory(String orderId);
}
