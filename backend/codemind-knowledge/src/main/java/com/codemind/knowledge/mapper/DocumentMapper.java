package com.codemind.knowledge.mapper;


import com.codemind.knowledge.entity.Document;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;


@Mapper
public interface DocumentMapper {


    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @Insert("""
        INSERT INTO document
        (
            knowledge_id,
            filename,
            file_path,
            file_size,
            file_type,
            creator_id
        )
        VALUES
        (
            #{knowledgeId},
            #{filename},
            #{filePath},
            #{fileSize},
            #{fileType},
            #{creatorId}
        )
        """)
    int insert(Document document);

}