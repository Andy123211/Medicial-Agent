package com.lin.store;

import com.lin.bean.ChatMessages;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MongoChatMemoryStore implements ChatMemoryStore {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        /**
         * 实现用id查询聊天记录
         */
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = Query.query(criteria);
        ChatMessages chatMessages = mongoTemplate.findOne(query, ChatMessages.class);

        if (chatMessages == null)
            return new ArrayList<>();
        return ChatMessageDeserializer.messagesFromJson(chatMessages.getContext());

    }
    /**
     * 实现更新聊天记录
     */
    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = Query.query(criteria);
        Update update = new Update();
        update.set("context", ChatMessageSerializer.messagesToJson( list));
        mongoTemplate.upsert(query, update, ChatMessages.class);


    }
    /**
     * 实现删除聊天记录
     */

    @Override
    public void deleteMessages(Object memoryId) {
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = Query.query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);

    }
}
