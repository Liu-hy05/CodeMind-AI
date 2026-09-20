package com.codemind.knowledge.entity;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DocumentChunk {


    /**
     * 主键ID
     */
    private Long id;


    /**
     * 所属文档ID
     */
    private Long documentId;


    /**
     * 文本块顺序
     */
    private Integer chunkIndex;


    /**
     * 文本内容
     */
    private String content;


    /**
     * token数量
     */
    private Integer tokenCount;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}