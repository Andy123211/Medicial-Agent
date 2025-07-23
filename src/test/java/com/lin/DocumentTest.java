package com.lin;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.*;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DocumentTest {
    @Test
    public void documentStoreTest()
    {
        Document document = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\人工智能.md");
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor.ingest(document, embeddingStore);
        System.out.println(embeddingStore);
    }
    @Test
    public void documentStoreTest1()
    {
        Document document = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\人工智能.md");
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        //定义文档分割器
        DocumentSplitter documentSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                new HuggingFaceTokenizer()
        );
        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentSplitter)
                .build()
                .ingest(document);
        System.out.println(embeddingStore);
    }
    @Autowired
    EmbeddingModel embeddingModel;
    @Test
    public void embeddingTest()
    {

        Response<Embedding> embeddingResponse = embeddingModel.embed("小智");
        //向量维度
        System.out.println("向量维度"+embeddingResponse.content().vector().length);
    }
    @Autowired
    EmbeddingStore<TextSegment> embeddingStore;
    @Test
    public void embeddingTest1()
    {
        TextSegment textSegment = TextSegment.from("我喜欢打羽毛球");
        Embedding embedding = embeddingModel.embed(textSegment).content();
        embeddingStore.add( embedding,textSegment);
        TextSegment textSegment1 = TextSegment.from("我喜欢吃火锅");
        Embedding embedding1 = embeddingModel.embed(textSegment1).content();
        embeddingStore.add( embedding1,textSegment1);


    }
    @Test
    public void searchTest()
    {
        Embedding embedding = embeddingModel.embed("你喜欢什么运动？").content();
        EmbeddingSearchRequest embeddingSearch = EmbeddingSearchRequest.builder()
                .queryEmbedding(embedding)
                .maxResults(1)
//                .minScore(0.5)
                .build();
        EmbeddingSearchResult<TextSegment> embeddingResult=
                embeddingStore.search(embeddingSearch);
        EmbeddingMatch<TextSegment> embeddingMatch = embeddingResult.matches().get(0);
        System.out.println(embeddingMatch.embedded().text());
    }
    @Test
    public void uploadTest1()
    {
        Document document = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\医院信息.md");
        Document document1 = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\神经内科.md");
    Document document2 = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\科室信息.md");
        List<Document> documents = Arrays.asList(document,document1,document2);
        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documents);
    }
    @Test
    public void uploadTest2()
    {
        Document document = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\医院信息.md");
//        Document document1 = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\神经内科.md");
//        Document document2 = FileSystemDocumentLoader.loadDocument("D:\\IdeaProjects\\Medical-Agent\\src\\main\\resources\\knowledge\\科室信息.md");
//        List<Document> documents = Arrays.asList(document,document1,document2);
        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(document);
    }
}
