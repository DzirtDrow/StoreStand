package com.tsystems.javaschool.brajnikov.storestand.bean;

import com.tsystems.javaschool.brajnikov.storestand.dto.PromotionDto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class PromotionsBean implements Serializable{
    @Inject
    private UpdateBean updateBean;

    public List<PromotionDto> getPromotions() {
        List<PromotionDto> result;// = Collections.emptyList();
        result = updateBean.getPromotions();
        return result;
    }

    private void updatePromotions(){
        updateBean.updatePromotionsList();
    }

    public void test(){

    }
}
