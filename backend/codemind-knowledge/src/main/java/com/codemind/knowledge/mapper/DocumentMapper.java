package com.codemind.knowledge.mapper;


import com.codemind.knowledge.entity.Document;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DocumentMapper {


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