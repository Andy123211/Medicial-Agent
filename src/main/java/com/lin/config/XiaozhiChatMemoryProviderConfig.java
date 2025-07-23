package com.lin.config;

import com.lin.store.MongoChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;

import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class XiaozhiChatMemoryProviderConfig {
    @Autowired
    MongoChatMemoryStore mongoChatMemoryStore;
    @Bean
    ChatMemoryProvider chatMemoryProviderXiaozhi(){
        return memoryId-> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }
    @Bean
    ContentRetriever contentRetrieverXiaozhi(){
        Document document = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\医院信息.md");
        Document document1 = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\口腔科.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\科室信息.md");
        //用arraylist进行存储
        List<Document> documents = Arrays.asList(document,document1,document2);
        //使用内存向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        //使用默认的文档分割器
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);

        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
    @Autowired
    private EmbeddingStore embeddingStore;
    @Autowired
    private EmbeddingModel embeddingModel;
    @Bean
    ContentRetriever contentRetrieverXiaozhi2()
    {
        return EmbeddingStoreContentRetriever.builder()

                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(1)
                .build();

    }
}
