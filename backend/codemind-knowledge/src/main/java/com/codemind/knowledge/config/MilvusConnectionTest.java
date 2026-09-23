package com.codemind.knowledge.config;


import io.milvus.v2.client.MilvusClientV2;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;


@Component
public class MilvusConnectionTest {


    @Resource
    private MilvusClientV2 milvusClient;



    @PostConstruct
    public void testConnection(){


        System.out.println("======================");

        System.out.println(
                "Milvus V2 Client 创建成功"
        );


        System.out.println(
                milvusClient
        );


        System.out.println("======================");

    }

}