package com.codemind.knowledge.service.impl;


import com.codemind.knowledge.service.EmbeddingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmbeddingServiceImpl
        implements EmbeddingService {


    @Override
    public List<Float> embed(
            String text
    ) {


        List<Float> vector =
                new ArrayList<>();


        for(int i = 0; i < 10; i++){

            vector.add(
                    (float) Math.random()
            );

        }


        return vector;

    }

}