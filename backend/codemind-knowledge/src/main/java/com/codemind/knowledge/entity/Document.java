package com.codemind.knowledge.entity;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Document {


    /**
     * 主键
     */
    private Long id;


    /**
     * 所属知识库ID
     */
    private Long knowledgeId;


    /**
     * 原文件名
     */
    private String filename;


    /**
     * 文件存储路径
     */
    private String filePath;


    /**
     * 文件大小
     */
    private Long fileSize;


    /**
     * 文件类型
     */
    private String fileType;


    /**
     * 上传用户ID
     */
    private Long creatorId;


    /**
     * 状态
     */
    private Integer status;


    /**
     * 逻辑删除
     */
    private Integer deleted;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}