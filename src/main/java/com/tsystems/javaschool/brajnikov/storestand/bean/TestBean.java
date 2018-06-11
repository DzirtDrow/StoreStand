package com.tsystems.javaschool.brajnikov.storestand.bean;

import com.tsystems.javaschool.brajnikov.storestand.dto.PromotionDto;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class TestBean {

    private PromotionDto promotionDto;

    @PostConstruct
    public void init() {

        promotionDto.setId(1);

    }

    public void testMyBean(int id, String name) {
        String result = id + " : " + name;
    }
}
