package com.example.demospringai.services;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class DataInitializer {

    @Autowired
    private VectorStore vectorStore;

    @PostConstruct
    public void init() {
        TextReader jobListReader = new TextReader(new ClassPathResource("job_listings.txt"));
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter(100,100,5,1000,true);
        List<Document> documents = tokenTextSplitter.split(jobListReader.get());
        vectorStore.add(documents);

        TextReader productDataReader = new TextReader(new ClassPathResource("product-data.txt"));
        TokenTextSplitter tokenTextSplitter1 = new TokenTextSplitter(100,100,5,1000,true);
        List<Document> documents1 = tokenTextSplitter1.split(productDataReader.get());
        vectorStore.add(documents1);
    }
}
