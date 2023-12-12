package com.example.Personalize.controller;

import com.example.Personalize.model.vo.RecommendationVo;
import com.example.Personalize.services.PersnalizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalize")
public class PersonalizeController {

    @Autowired
    PersnalizeService personalizeService;
    @GetMapping("/viewrecommendation")
    public List<RecommendationVo> getRecommedationList(@RequestParam(value="cid") Long customerId){
        return personalizeService.getRecommendation(customerId);
    }


    }

