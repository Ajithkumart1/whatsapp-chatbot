package com.chatbot.controller;

import com.chatbot.model.MessageRequest;
import com.chatbot.model.MessageResponse;
import com.chatbot.service.ChatbotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private static final Logger logger =
        LoggerFactory.getLogger(WebhookController.class);

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public ResponseEntity<MessageResponse> receiveMessage(
            @RequestBody MessageRequest request) {
        logger.info("POST /webhook received");
        MessageResponse response = chatbotService.processMessage(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
            "status", "WhatsApp Chatbot is running!",
            "version", "1.0.0"
        ));
    }
}