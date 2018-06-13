package com.tsystems.javaschool.brajnikov.storestand.socket;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;
import javax.faces.application.FacesMessage;

@PushEndpoint("/notify")
@Singleton
public class NotifyResource {

    @OnMessage(encoders = { JSONEncoder.class })
    public FacesMessage onMessage(FacesMessage message) {
        return message;
    }
}
