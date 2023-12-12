package com.example.Personalize.dao.api;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Personalize.model.entity.Recommendation;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {


}
