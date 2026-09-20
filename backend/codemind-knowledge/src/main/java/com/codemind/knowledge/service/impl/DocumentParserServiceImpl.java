package com.codemind.knowledge.service.impl;


import com.codemind.knowledge.service.DocumentParserService;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class DocumentParserServiceImpl
        implements DocumentParserService {


    @Override
    public String parsePdf(
            String filePath
    ) {

        if(filePath == null){

            throw new RuntimeException(
                    "文件路径为空"
            );

        }

        try {


            File file =
                    new File(filePath);


            PDDocument document =
                    Loader.loadPDF(file);


            PDFTextStripper stripper =
                    new PDFTextStripper();


            String text =
                    stripper.getText(document);


            document.close();


            return text;


        } catch (IOException e) {


            throw new RuntimeException(
                    "PDF解析失败",
                    e
            );

        }

    }

}