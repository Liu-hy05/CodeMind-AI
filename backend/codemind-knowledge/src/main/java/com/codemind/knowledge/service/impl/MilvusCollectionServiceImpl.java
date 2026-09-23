package com.codemind.knowledge.service.impl;


import com.codemind.knowledge.service.MilvusCollectionService;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import io.milvus.v2.service.collection.request.CreateCollectionReq.CollectionSchema;
import io.milvus.v2.service.collection.request.CreateCollectionReq.FieldSchema;
import io.milvus.v2.service.collection.request.HasCollectionReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;


@Service
public class MilvusCollectionServiceImpl
        implements MilvusCollectionService {


    @Resource
    private MilvusClientV2 milvusClient;



    @Override
    public void createCollection() {


        if(hasCollection()){


            System.out.println(
                    "document_vectors 已存在"
            );


            return;

        }



        // 主键
        FieldSchema idField =
                FieldSchema.builder()
                        .name("id")
                        .dataType(DataType.Int64)
                        .isPrimaryKey(true)
                        .autoID(true)
                        .build();



        // 对应MySQL document_chunk.id
        FieldSchema chunkIdField =
                FieldSchema.builder()
                        .name("chunk_id")
                        .dataType(DataType.Int64)
                        .build();



        // 文本内容
        FieldSchema contentField =
                FieldSchema.builder()
                        .name("content")
                        .dataType(DataType.VarChar)
                        .maxLength(65535)
                        .build();



        // 向量
        FieldSchema vectorField =
                FieldSchema.builder()
                        .name("vector")
                        .dataType(DataType.FloatVector)
                        .dimension(768)
                        .build();



        List<FieldSchema> fields =
                Arrays.asList(
                        idField,
                        chunkIdField,
                        contentField,
                        vectorField
                );



        CollectionSchema schema =
                CollectionSchema.builder()
                        .fieldSchemaList(fields)
                        .build();



        CreateCollectionReq request =
                CreateCollectionReq.builder()
                        .collectionName(
                                "document_vectors"
                        )
                        .collectionSchema(schema)
                        .build();



        milvusClient.createCollection(
                request
        );



        System.out.println(
                "document_vectors 创建成功"
        );

    }





    @Override
    public boolean hasCollection() {


        HasCollectionReq request =
                HasCollectionReq.builder()
                        .collectionName(
                                "document_vectors"
                        )
                        .build();



        return milvusClient.hasCollection(
                request
        );

    }


}