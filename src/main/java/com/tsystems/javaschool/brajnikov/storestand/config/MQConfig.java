package com.tsystems.javaschool.brajnikov.storestand.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ejb.Singleton;

@Singleton
@Getter
public class MQConfig {

    //private String exchangeName = "store-exchange";

    @AllArgsConstructor
    @Getter
    public enum Queue {
        PROMO("promo_queue", "promo.*");
//        SALES("sales-queue", "sale.*"),
//        GENERAL("general-queue", "general.*");

        private String name;
        private String key;
    }

    @AllArgsConstructor
    @Getter
    public class ConnectionDetails {
        public static final String HOST = "127.0.0.1";
        public static final int PORT = 5672;
        public static final String USER = "guest";
        public static final String PASSWORD = "guest";
    }
}