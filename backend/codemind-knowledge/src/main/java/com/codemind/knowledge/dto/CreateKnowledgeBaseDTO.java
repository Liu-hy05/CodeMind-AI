package com.codemind.knowledge.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CreateKnowledgeBaseDTO {


    /**
     * 知识库名称
     */
    @NotBlank(message = "知识库名称不能为空")
    private String name;


    /**
     * 描述
     */
    private String description;


}