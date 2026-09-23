package com.codemind.knowledge.controller;


import com.codemind.knowledge.service.MilvusCollectionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/milvus")
public class MilvusController {


    @Resource
    private MilvusCollectionService milvusCollectionService;



    @PostMapping("/create")
    public String create(){


        milvusCollectionService.createCollection();


        return "success";

    }

}