package com.tsystems.javaschool.brajnikov.storestand.bean;

import com.tsystems.javaschool.brajnikov.storestand.dto.GoodsDto;
import com.tsystems.javaschool.brajnikov.storestand.dto.PromotionDto;
import com.tsystems.javaschool.brajnikov.storestand.service.UpdateService;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UpdateBean {
    @Inject
    private UpdateService updateService;

    @Getter
    private List<PromotionDto> promotions;

    @Getter
    private List<GoodsDto> topGoodsList;

    @Inject
    @Push(channel = "promo")
    private PushContext pushContext;

    @PostConstruct
    public void init() {
        updateService.init();
        updatePromotionsList();
        updateTopGoodsList();
    }

    public  void updateTopGoodsList(){
        topGoodsList = updateService.updateTopGoods();
    }

    public void updatePromotionsList() {
        promotions = updateService.updatePromotions();
        pushContext.send("promoupd");
    }

}
