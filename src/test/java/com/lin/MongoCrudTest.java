package com.lin;


import com.lin.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoCrudTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 测试保存
     */
    @Test
    public void testSave()
    {
        ChatMessages chatMessage = new ChatMessages();
        chatMessage.setContext("hello world");
        mongoTemplate.insert(chatMessage);
    }
    /**
     * 根据id查询文档
     */
    @Test
    public void testFind()
    {
        ChatMessages chatMessage = mongoTemplate.findById(0, ChatMessages.class);
        System.out.println(chatMessage);
    }
    /**
     * 修改文档
     */
    @Test
    public void testUpdate()
    {
        //使用criteria查询
        Criteria criteria = Criteria.where("id").is(0);
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("context","hello world update");
        mongoTemplate.upsert(query,update,ChatMessages.class);
    }
    /**
     * 删除文档
     */
    @Test
    public void testDelete()
    {
        //使用criteria查询
        Criteria criteria = Criteria.where("id").is(0);
        Query query = new Query(criteria);
        mongoTemplate.remove(query,ChatMessages.class);
    }


}
