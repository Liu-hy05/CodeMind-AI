package com.codemind.knowledge.mapper;


import com.codemind.knowledge.entity.Document;
import org.apache.ibatis.annotations.*;


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

    @Results({
            @Result(
                    property = "id",
                    column = "id"
            ),

            @Result(
                    property = "knowledgeId",
                    column = "knowledge_id"
            ),

            @Result(
                    property = "filename",
                    column = "filename"
            ),

            @Result(
                    property = "filePath",
                    column = "file_path"
            ),

            @Result(
                    property = "fileSize",
                    column = "file_size"
            ),

            @Result(
                    property = "fileType",
                    column = "file_type"
            ),

            @Result(
                    property = "creatorId",
                    column = "creator_id"
            ),

            @Result(
                    property = "status",
                    column = "status"
            ),

            @Result(
                    property = "deleted",
                    column = "deleted"
            ),

            @Result(
                    property = "createTime",
                    column = "create_time"
            ),

            @Result(
                    property = "updateTime",
                    column = "update_time"
            )
    })
    @Select("""
        SELECT *
        FROM document
        WHERE id = #{id}
        """)
    Document selectById(Long id);

}