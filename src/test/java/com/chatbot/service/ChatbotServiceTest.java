package com.chatbot.service;

import com.chatbot.model.MessageRequest;
import com.chatbot.model.MessageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatbotServiceTest {
    @Autowired
    private ChatbotService chatbotService;

    @Test
    void testHiReply() {
        MessageRequest req = new MessageRequest();
        req.setFrom("+91-9999999999");
        req.setMessage("Hi");
        MessageResponse res = chatbotService.processMessage(req);
        assertTrue(res.getReply().toLowerCase().contains("hello"));
    }

    @Test
    void testByeReply() {
        MessageRequest req = new MessageRequest();
        req.setFrom("+91-9999999999");
        req.setMessage("Bye");
        MessageResponse res = chatbotService.processMessage(req);
        assertTrue(res.getReply().toLowerCase().contains("goodbye") ||
                   res.getReply().toLowerCase().contains("bye"));
    }

    @Test
    void testUnknownMessage() {
        MessageRequest req = new MessageRequest();
        req.setFrom("+91-9999999999");
        req.setMessage("random xyz");
        MessageResponse res = chatbotService.processMessage(req);
        assertNotNull(res.getReply());
        assertFalse(res.getReply().isBlank());
    }

    @Test
    void testHelpReply() {
        MessageRequest req = new MessageRequest();
        req.setFrom("+91-9999999999");
        req.setMessage("help");
        MessageResponse res = chatbotService.processMessage(req);
        assertNotNull(res.getReply());
    }
}