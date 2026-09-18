package com.codemind.knowledge.controller;


import com.codemind.common.context.UserContext;
import com.codemind.common.result.Result;
import com.codemind.knowledge.dto.CreateKnowledgeBaseDTO;
import com.codemind.knowledge.entity.KnowledgeBase;
import com.codemind.knowledge.service.KnowledgeBaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/knowledge")
public class KnowledgeBaseController {


    private final KnowledgeBaseService knowledgeBaseService;


    public KnowledgeBaseController(
            KnowledgeBaseService knowledgeBaseService
    ){
        this.knowledgeBaseService = knowledgeBaseService;
    }



    @PostMapping("/create")
    public Result<Void> create(
            @Valid @RequestBody CreateKnowledgeBaseDTO dto
    ){

        Long userId = UserContext.getUserId();

        if(userId == null){

            return Result.error("请先登录");

        }


        KnowledgeBase knowledgeBase = new KnowledgeBase();


        knowledgeBase.setName(dto.getName());

        knowledgeBase.setDescription(
                dto.getDescription()
        );


        knowledgeBase.setCreatorId(userId);

        knowledgeBase.setStatus(1);

        knowledgeBase.setDeleted(0);


        knowledgeBaseService.create(
                knowledgeBase
        );


        return Result.success();

    }

}