package com.codemind.knowledge.service.impl;


import com.codemind.common.context.UserContext;
import com.codemind.common.exception.BusinessException;
import com.codemind.knowledge.entity.Document;
import com.codemind.knowledge.mapper.DocumentMapper;
import com.codemind.knowledge.service.DocumentService;

import com.codemind.knowledge.vo.DocumentVO;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;


@Service
public class DocumentServiceImpl
        implements DocumentService {


    @Resource
    private DocumentMapper documentMapper;


    private static final String FILE_PATH =
            "E:/codemind-files/";


    @Override
    public DocumentVO upload(
            Long knowledgeId,
            MultipartFile file
    ) {


        if(file == null || file.isEmpty()){

            throw new BusinessException(
                    "文件不能为空"
            );

        }


        Long userId =
                UserContext.getUserId();


        try {


            File dir =
                    new File(FILE_PATH);


            if(!dir.exists()){

                dir.mkdirs();

            }


            String filename =
                    file.getOriginalFilename();


            String suffix = "";


            if(filename != null
                    && filename.contains(".")){

                suffix =
                        filename.substring(
                                filename.lastIndexOf(".")
                        );
            }


            String newFilename =
                    java.util.UUID.randomUUID()
                            + suffix;


            File target =
                    new File(
                            FILE_PATH + newFilename
                    );


            file.transferTo(target);


            Document document =
                    new Document();


            document.setKnowledgeId(
                    knowledgeId
            );


            document.setFilename(
                    filename
            );


            document.setFilePath(
                    target.getAbsolutePath()
            );


            document.setFileSize(
                    file.getSize()
            );


            document.setFileType(
                    file.getContentType()
            );


            document.setCreatorId(
                    userId
            );


            int rows =
                    documentMapper.insert(document);


            if(rows == 0){

                throw new BusinessException(
                        "文件记录保存失败"
                );

            }

            DocumentVO vo =
                    new DocumentVO();


            vo.setId(
                    document.getId()
            );


            vo.setFilename(
                    document.getFilename()
            );


            vo.setFileSize(
                    document.getFileSize()
            );


            vo.setFileType(
                    document.getFileType()
            );


            return vo;


        } catch (IOException e) {


            throw new BusinessException(
                    "文件上传失败"
            );

        }

    }

}