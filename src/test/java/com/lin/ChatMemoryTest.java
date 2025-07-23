package com.lin;

import com.lin.assistant.Assistant;
import com.lin.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    QwenChatModel qwenChatModel;
    @Test
    public void testChatMemory()
    {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        Assistant assistant= AiServices.builder(
                Assistant.class)
                .chatMemory(chatMemory)
                .chatLanguageModel(qwenChatModel)
                .build();
        String chat1 = assistant.chat("你好,我是欢欢");
        System.out.println(chat1);
        String chat2 = assistant.chat("我是谁");
        System.out.println(chat2);



    }
    @Autowired
    Assistant assistant;
    @Test
    public void testChatMemory1()
    {
        String chat = assistant.chat("你好,我是欢欢");
        System.out.println(chat);
        String chat1 = assistant.chat("我是谁");
        System.out.println(chat1);


    }
    @Autowired
    SeparateChatAssistant separateChatAssistant;
    @Test
    public void testChatMemory2()
    {
        String chat = separateChatAssistant.chat(1,"你好,我是欢欢");
        System.out.println(chat);
        String chat1 = separateChatAssistant.chat(1,"我是谁");
        System.out.println(chat1);
        String chat2 = separateChatAssistant.chat(2,"我是谁");
        System.out.println(chat2);


    }
}
