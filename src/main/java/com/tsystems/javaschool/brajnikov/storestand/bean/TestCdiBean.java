package com.tsystems.javaschool.brajnikov.storestand.bean;

import lombok.Data;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

@Named
@Data
@ApplicationScoped
public class TestCdiBean {
    int id = 1;
    String name = "name";

    @EJB
    TestBean testBean;

    public void test() {
        testBean.testMyBean(id, name);
    }
}
