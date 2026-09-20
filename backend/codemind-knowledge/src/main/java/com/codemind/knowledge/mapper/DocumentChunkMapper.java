package com.codemind.knowledge.mapper;


import com.codemind.knowledge.entity.DocumentChunk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DocumentChunkMapper {


    @Insert("""
            INSERT INTO document_chunk
            (
                document_id,
                chunk_index,
                content,
                token_count
            )
            VALUES
            (
                #{documentId},
                #{chunkIndex},
                #{content},
                #{tokenCount}
            )
            """)
    int insert(DocumentChunk documentChunk);


}