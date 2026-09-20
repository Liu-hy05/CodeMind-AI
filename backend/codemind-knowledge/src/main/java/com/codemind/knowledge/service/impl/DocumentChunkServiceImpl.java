package com.codemind.knowledge.service.impl;


import com.codemind.knowledge.entity.DocumentChunk;
import com.codemind.knowledge.mapper.DocumentChunkMapper;
import com.codemind.knowledge.service.DocumentChunkService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class DocumentChunkServiceImpl
        implements DocumentChunkService {


    @Resource
    private DocumentChunkMapper documentChunkMapper;


    @Override
    public void splitAndSave(
            Long documentId,
            String text
    ) {


        int chunkSize = 1000;


        int index = 0;


        for(
                int start = 0;
                start < text.length();
                start += chunkSize
        ){


            int end =
                    Math.min(
                            start + chunkSize,
                            text.length()
                    );


            String content =
                    text.substring(
                            start,
                            end
                    );


            DocumentChunk chunk =
                    new DocumentChunk();


            chunk.setDocumentId(documentId);


            chunk.setChunkIndex(index);


            chunk.setContent(content);


            chunk.setTokenCount(
                    content.length()
            );


            documentChunkMapper.insert(chunk);


            index++;

        }

    }

}