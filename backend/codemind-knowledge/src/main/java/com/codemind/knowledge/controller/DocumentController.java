package com.codemind.knowledge.controller;


import com.codemind.common.result.Result;
import com.codemind.knowledge.entity.Document;
import com.codemind.knowledge.mapper.DocumentMapper;
import com.codemind.knowledge.service.DocumentParserService;
import com.codemind.knowledge.service.DocumentProcessService;
import com.codemind.knowledge.service.DocumentService;

import com.codemind.knowledge.service.EmbeddingService;
import com.codemind.knowledge.vo.DocumentVO;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/document")
public class DocumentController {


    @Resource
    private DocumentService documentService;

    @Resource
    private DocumentMapper documentMapper;


    @Resource
    private DocumentParserService documentParserService;

    @Resource
    private DocumentProcessService documentProcessService;

    @Resource
    private EmbeddingService embeddingService;


    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public Result<DocumentVO> upload(
            @RequestParam("knowledgeId")
            Long knowledgeId,

            @RequestParam("file")
            MultipartFile file
    ) {

        DocumentVO vo =
                documentService.upload(
                        knowledgeId,
                        file
                );


        return Result.success(vo);

    }

    @GetMapping("/parse/{id}")
    public Result<String> parse(
            @PathVariable("id") Long id
    ) {

        Document document =
                documentMapper.selectById(id);

        System.out.println(
                "查询文件路径:"
                        + document.getFilePath()
        );

        if (document == null) {

            return Result.error(
                    "文档不存在"
            );

        }


        String text =
                documentParserService.parsePdf(
                        document.getFilePath()
                );


        return Result.success(text);

    }

    @PostMapping("/process/{id}")
    public Result<Void> process(
            @PathVariable("id") Long id
    ) {


        documentProcessService.processDocument(id);


        return Result.success();

    }

    @GetMapping("/embed/test")
    public Result<List<Float>> embedTest() {


        List<Float> vector =
                embeddingService.embed(
                        "Java异常处理规范"
                );


        return Result.success(vector);

    }
}