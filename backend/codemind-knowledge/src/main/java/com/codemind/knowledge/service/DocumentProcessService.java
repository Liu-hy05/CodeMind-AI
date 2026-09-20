package com.codemind.knowledge.service;


public interface DocumentProcessService {


    /**
     * 解析并保存文档chunk
     *
     * @param documentId 文档ID
     */
    void processDocument(
            Long documentId
    );

}