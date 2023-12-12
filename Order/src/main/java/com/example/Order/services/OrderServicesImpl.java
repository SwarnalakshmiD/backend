package com.example.Order.services;


import com.example.Order.api.OrderRepository;
import com.example.Order.model.entity.Order;
import com.example.Order.model.entity.OrderVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("orderServices")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    RestTemplate restTemplate;

    private String inventoryUrl="http://localhost:8091/inventory";

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrderDetails()
    {
            return orderRepository.findAll();
    }

    public List<Order> getOrderHistory(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return Collections.singletonList(order);
        } else {
            return Collections.emptyList();
        }
    }

    public String checkProductAvailability(Long id,int quantity)
    {
        System.out.println("-----------------"+quantity);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(inventoryUrl + "/checkproductquantity")
                .queryParam("id", id)
                .queryParam("quantity",quantity)
                .build();
        return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class).getBody();
    }

    public String addOrderDetails(Order order)
    {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("-------"+order.getProductQuantity());
        String s=checkProductAvailability(order.getProductId(),order.getProductQuantity());
        System.out.println(s);
        if(s.equals("Sufficient quantity available")){
        orderRepository.save(objectMapper.convertValue(order, com.example.Order.model.entity.Order.class));
        return "order added successfully";
    }
        return "order cant be placed";
    }


}
