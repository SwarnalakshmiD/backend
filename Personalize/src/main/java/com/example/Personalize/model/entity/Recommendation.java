package com.example.Personalize.model.entity;

import com.example.Personalize.model.constant.FieldName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = FieldName.RECOMMENDATION_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
        @Id
        @Column(name = FieldName.CID)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long cid;

        @Column(name = FieldName.CNAME, nullable  = false)
        String cname;

        @Column(name = FieldName.CATEGORY_ID, nullable  = false)
        Long categoryId;
}
