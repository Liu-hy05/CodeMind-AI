package com.codemind.knowledge.service;


import com.codemind.knowledge.entity.DocumentChunk;

import java.util.List;


public interface DocumentChunkService {


    /**
     * 文本切片
     *
     * @param documentId 文档ID
     * @param text 文本内容
     */
    void splitAndSave(
            Long documentId,
            String text
    );


}