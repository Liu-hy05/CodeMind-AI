package com.codemind.knowledge.config;


import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MilvusConfig {


    @Value("${milvus.host}")
    private String host;


    @Value("${milvus.port}")
    private int port;



    @Bean
    public MilvusServiceClient milvusClient(){


        return new MilvusServiceClient(
                ConnectParam.newBuilder()
                        .withHost(host)
                        .withPort(port)
                        .build()
        );

    }

}