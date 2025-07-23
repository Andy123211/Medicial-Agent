package com.lin.assistant;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;
//import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory")
public interface Assistant {
    String chat (String question);

}
