package com.codemind.knowledge.service;


import com.codemind.knowledge.vo.DocumentVO;
import org.springframework.web.multipart.MultipartFile;


public interface DocumentService {


    /**
     * 上传文件
     *
     * @param knowledgeId 所属知识库
     * @param file 文件
     */
    DocumentVO upload(
            Long knowledgeId,
            MultipartFile file
    );

}