package com.codemind.knowledge.service;


public interface DocumentParserService {


    /**
     * 解析PDF文件
     *
     * @param filePath 文件路径
     * @return PDF文本内容
     */
    String parsePdf(
            String filePath
    );

}