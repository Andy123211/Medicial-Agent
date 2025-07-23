package com.lin.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.spring.AiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryAssistantConfig {
    @Bean
    ChatMemory chatMemory()
    {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

}
