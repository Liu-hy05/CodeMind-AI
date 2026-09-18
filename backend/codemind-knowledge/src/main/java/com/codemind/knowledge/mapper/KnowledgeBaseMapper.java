package com.codemind.knowledge.mapper;


import com.codemind.knowledge.entity.KnowledgeBase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface KnowledgeBaseMapper {


    @Insert("""
            INSERT INTO knowledge_base
            (
                name,
                description,
                creator_id,
                status,
                deleted
            )
            VALUES
            (
                #{name},
                #{description},
                #{creatorId},
                #{status},
                #{deleted}
            )
            """)
    int insert(KnowledgeBase knowledgeBase);

}