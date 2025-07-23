package com.lin.controller;

import com.lin.assistant.XiaozhiAgent;
import com.lin.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "小智")
@RestController
@RequestMapping("/xiaozhi")
public class XiaozhiController {
    @Autowired
    private XiaozhiAgent XiaozhiAgent;
    @Operation(summary = "对话")
    @PostMapping("/chat")
    public Flux<String> chat(@RequestBody ChatForm chatForm)
    {

        return XiaozhiAgent.chat(chatForm.getMenoryId(),chatForm.getMessage());
    }
}
