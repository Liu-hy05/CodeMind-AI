package com.codemind.knowledge.service;


public interface MilvusCollectionService {


    /**
     * 创建向量集合
     */
    void createCollection();


    /**
     * 判断集合是否存在
     */
    boolean hasCollection();


}