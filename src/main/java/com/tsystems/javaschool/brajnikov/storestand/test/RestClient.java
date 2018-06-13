package com.tsystems.javaschool.brajnikov.storestand.test;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tsystems.javaschool.brajnikov.storestand.dto.PromotionDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;


public class RestClient {
    public static void main(String[] args) throws IOException {
        String uri = "http://localhost:8086/internet-store/rest/rtest/promolist";

        Client client = Client.create();
        WebResource webResource = client.resource(uri);

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            System.out.println("Response status: " + response.getStatus());
        }

        String responseJsonString = response.getEntity(String.class);
        System.out.println(responseJsonString);

        ObjectMapper mapper = new ObjectMapper();
        List<PromotionDto> promoList = mapper.readValue(responseJsonString, new TypeReference<List<PromotionDto>>(){});

        System.out.println(promoList);

    }
}

