package com.tsystems.javaschool.brajnikov.storestand.bean;

import com.tsystems.javaschool.brajnikov.storestand.dto.GoodsDto;
import com.tsystems.javaschool.brajnikov.storestand.dto.PromotionDto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TopGoodsBean implements Serializable {
    @Inject
    private UpdateBean updateBean;

    public List<GoodsDto> getTopGoods() {
        List<GoodsDto> result;// = Collections.emptyList();
        result = updateBean.getTopGoodsList();
        return result;
    }

}
