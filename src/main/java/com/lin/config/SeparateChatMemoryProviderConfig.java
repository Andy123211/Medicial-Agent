package com.lin.config;

import com.lin.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SeparateChatMemoryProviderConfig {
    @Autowired
    MongoChatMemoryStore mongoChatMemoryStore;
    @Bean
    ChatMemoryProvider chatMemoryProvider(){
        return memoryId-> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }
}
