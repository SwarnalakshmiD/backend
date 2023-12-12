package com.example.Personalize.services;

import com.example.Personalize.model.vo.RecommendationVo;

import java.util.List;

public interface PersnalizeService {
    List<RecommendationVo> getRecommendation(Long customerId);


}
