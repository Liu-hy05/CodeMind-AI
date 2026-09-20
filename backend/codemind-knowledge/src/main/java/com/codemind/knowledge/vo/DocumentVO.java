package com.codemind.knowledge.vo;


import lombok.Data;


@Data
public class DocumentVO {


    /**
     * 文档ID
     */
    private Long id;


    /**
     * 原文件名
     */
    private String filename;


    /**
     * 文件大小
     */
    private Long fileSize;


    /**
     * 文件类型
     */
    private String fileType;


}