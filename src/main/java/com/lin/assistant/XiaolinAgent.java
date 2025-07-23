package com.lin.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
            streamingChatModel = "qwenStreamingChatModel",
            chatMemoryProvider = "chatMemoryProviderXiaozhi",
            tools = "appointTools",
            contentRetriever = "contentRetrieverXiaozhi2"

            )
public interface XiaolinAgent {
    @SystemMessage(fromResource = "tielu-prompt-template.txt")
    Flux<String> chat(@MemoryId int memoryId, @UserMessage String question);
}
