package com.codemind.knowledge.config;


import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.client.ConnectConfig;
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
    public MilvusClientV2 milvusClient(){


        ConnectConfig config =
                ConnectConfig.builder()
                        .uri(
                                "http://"
                                        + host
                                        + ":"
                                        + port
                        )
                        .build();



        return new MilvusClientV2(
                config
        );

    }

}