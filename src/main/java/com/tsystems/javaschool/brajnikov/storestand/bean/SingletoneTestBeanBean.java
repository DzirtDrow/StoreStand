package com.tsystems.javaschool.brajnikov.storestand.bean;

import javax.ejb.Init;
import javax.ejb.Singleton;

@Singleton
public class SingletoneTestBeanBean {

    @Init
    public void init() {
        String s = "segsabr";
        System.out.println(s);
    }
}
