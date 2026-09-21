package com.codemind.knowledge.service;


import java.util.List;


public interface EmbeddingService {


    /**
     * 文本转向量
     *
     * @param text 文本
     * @return 向量
     */
    List<Float> embed(
            String text
    );

}