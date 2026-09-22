package com.codemind.knowledge.config;


import io.milvus.client.MilvusServiceClient;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;


@Component
public class MilvusConnectionTest {


    @Resource
    private MilvusServiceClient milvusClient;


    @PostConstruct
    public void testConnection(){


        System.out.println("======================");

        System.out.println(
                "Milvus Client 创建成功"
        );

        System.out.println(
                milvusClient
        );

        System.out.println("======================");


    }

}