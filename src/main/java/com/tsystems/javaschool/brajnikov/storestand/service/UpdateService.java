package com.tsystems.javaschool.brajnikov.storestand.service;

import com.rabbitmq.client.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tsystems.javaschool.brajnikov.storestand.bean.UpdateBean;
import com.tsystems.javaschool.brajnikov.storestand.config.MQConfig;
import com.tsystems.javaschool.brajnikov.storestand.dto.GoodsDto;
import com.tsystems.javaschool.brajnikov.storestand.dto.PromotionDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;

import javax.ejb.Singleton;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Singleton
public class UpdateService {
    private static final String QUEUE_NAME = "promo_queue";

    //    private static String PROMO_URI = "http://localhost:8085/internet-store/rest/rtest/promolist";
    private static String PROMO_URI = "http://localhost:8085/internet-store/test";
    private TopicKey promoTopicKey = new TopicKey("promo");
    @Inject
    private BeanManager beanManager;

    @Inject
    MQConfig mqConfig;

    @Inject
    UpdateBean updateBean;


    public void init() {
        ConnectionFactory factory = buildConnectionFactory();
        initializeTopic();
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    updateBean.updatePromotionsList();
                    updateBean.updateTopGoodsList();

//                    sendMessageToPage();


                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }

    //    private void notifyPush() {
//        String summary = "Update";
//        String detail = "Updating data in table";
//        String CHANNEL = "/notify";
//        EventBus eventBus = EventBusFactory.getDefault().eventBus();
//        eventBus.publish(CHANNEL, new FacesMessage(summary, detail));
//    }
    public void initializeTopic() {
        TopicsContext topicsContext = TopicsContext.lookup();
        topicsContext.getOrCreateTopic(promoTopicKey);
    }

    public void sendMessageToPage() {
        try {
            TopicsContext topicsContext = TopicsContext.lookup();
            topicsContext.publish(promoTopicKey, "message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory buildConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQConfig.ConnectionDetails.HOST);
        factory.setPort(MQConfig.ConnectionDetails.PORT);
        factory.setUsername(MQConfig.ConnectionDetails.USER);
        factory.setPassword(MQConfig.ConnectionDetails.PASSWORD);

        return factory;
    }

    public List<PromotionDto> updatePromotions() {
        return getResponse("/promo");
    }

    public List<GoodsDto> updateTopGoods() {
        Client client = Client.create();
        WebResource webResource = client.resource(PROMO_URI + "/topgoods");

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            return Collections.emptyList();
        }
        String responseJsonString = response.getEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<GoodsDto> topGoodsList = Collections.emptyList();
        try {
            topGoodsList = mapper.readValue(responseJsonString, new TypeReference<List<GoodsDto>>() {
            });
        } catch (IOException ex) {

        }
        return topGoodsList;
    }

    private List<PromotionDto> getResponse(String endpoint) {

        Client client = Client.create();
        WebResource webResource = client.resource(PROMO_URI + endpoint);

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            return Collections.emptyList();
        }
        String responseJsonString = response.getEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<PromotionDto> promoList = Collections.emptyList();
        try {
            promoList = mapper.readValue(responseJsonString, new TypeReference<List<PromotionDto>>() {
            });
        } catch (IOException ex) {

        }
        return promoList;
    }

}
