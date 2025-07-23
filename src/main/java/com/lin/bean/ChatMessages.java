package com.lin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chat_message")
public class ChatMessages {
    //唯一标识，映射到 MongoDB 文档的 _id 字
    @Id
    private ObjectId id;
    //消息id
    private int memoryId;
    private String context;
}
