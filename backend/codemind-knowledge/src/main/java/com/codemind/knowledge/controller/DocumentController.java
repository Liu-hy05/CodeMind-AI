package com.codemind.knowledge.controller;


import com.codemind.common.result.Result;
import com.codemind.knowledge.service.DocumentService;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/document")
public class DocumentController {


    @Resource
    private DocumentService documentService;



    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public Result<Void> upload(
            @RequestParam("knowledgeId")
            Long knowledgeId,

            @RequestParam("file")
            MultipartFile file
    ){

        documentService.upload(
                knowledgeId,
                file
        );


        return Result.success();

    }

}