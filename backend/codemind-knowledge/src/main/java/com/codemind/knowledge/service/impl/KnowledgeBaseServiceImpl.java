package com.codemind.knowledge.service.impl;


import com.codemind.knowledge.entity.KnowledgeBase;
import com.codemind.knowledge.mapper.KnowledgeBaseMapper;
import com.codemind.knowledge.service.KnowledgeBaseService;
import org.springframework.stereotype.Service;


@Service
public class KnowledgeBaseServiceImpl
        implements KnowledgeBaseService {


    private final KnowledgeBaseMapper knowledgeBaseMapper;


    public KnowledgeBaseServiceImpl(
            KnowledgeBaseMapper knowledgeBaseMapper
    ) {
        this.knowledgeBaseMapper = knowledgeBaseMapper;
    }



    @Override
    public void create(KnowledgeBase knowledgeBase) {

        knowledgeBaseMapper.insert(knowledgeBase);

    }

}