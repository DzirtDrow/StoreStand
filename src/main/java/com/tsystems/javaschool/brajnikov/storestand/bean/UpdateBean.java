package com.tsystems.javaschool.brajnikov.storestand.bean;

import com.tsystems.javaschool.brajnikov.storestand.dto.PromotionDto;
import com.tsystems.javaschool.brajnikov.storestand.service.UpdateService;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UpdateBean {
    @Inject
    private UpdateService updateService;

    @Getter
    private List<PromotionDto> promotions;


    @PostConstruct
    public void init() {
        updateService.init();
        updatePromotionsList();
    }

    public void updatePromotionsList() {
        promotions = updateService.updatePromotions();
    }

}
