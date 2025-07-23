package com.lin;

import com.lin.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {
    @Autowired
    Assistant assistant;
    @Test
    public void testChat() {
        //创建AIService

        String answer = assistant.chat("你好");
        System.out.println(answer);

    }
}
