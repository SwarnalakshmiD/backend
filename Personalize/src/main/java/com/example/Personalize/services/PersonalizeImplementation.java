package com.example.Personalize.services;

import com.example.Personalize.dao.api.RecommendationRepository;
import com.example.Personalize.model.entity.Recommendation;
import com.example.Personalize.model.vo.ProductVo;
import com.example.Personalize.model.vo.RecommendationVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service("personalizeService")
public class PersonalizeImplementation implements PersnalizeService {
    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<RecommendationVo> getRecommendation(Long customerId){
        List<Recommendation> recommendation =
                recommendationRepository.findAllById(Collections.singleton(customerId));
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.convertValue(recommendation, List.class));
        return objectMapper.convertValue(recommendation, List.class);
    }



        @KafkaListener(topics = "com.quinbay.product.create", groupId = "group-id")
        public void listen(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductVo productVo= objectMapper.readValue(message, ProductVo.class);
        System.out.println("notification received: " + productVo);
    }
    }


