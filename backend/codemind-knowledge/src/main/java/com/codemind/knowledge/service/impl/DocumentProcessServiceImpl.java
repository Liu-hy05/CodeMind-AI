package com.codemind.knowledge.service.impl;


import com.codemind.knowledge.entity.Document;
import com.codemind.knowledge.mapper.DocumentMapper;
import com.codemind.knowledge.service.DocumentChunkService;
import com.codemind.knowledge.service.DocumentParserService;
import com.codemind.knowledge.service.DocumentProcessService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class DocumentProcessServiceImpl
        implements DocumentProcessService {


    @Resource
    private DocumentMapper documentMapper;


    @Resource
    private DocumentParserService documentParserService;


    @Resource
    private DocumentChunkService documentChunkService;



    @Override
    public void processDocument(
            Long documentId
    ){

        Document document =
                documentMapper.selectById(
                        documentId
                );


        String text =
                documentParserService.parsePdf(
                        document.getFilePath()
                );


        documentChunkService.splitAndSave(
                documentId,
                text
        );

    }

}