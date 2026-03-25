package com.chatbot.service;

import com.chatbot.model.MessageRequest;
import com.chatbot.model.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {
    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Map<String, String> REPLY_MAP = new HashMap<>();

    static {
        REPLY_MAP.put("hi",        "Hello! How can I help you today?");
        REPLY_MAP.put("hello",     "Hey there! What can I do for you?");
        REPLY_MAP.put("bye",       "Goodbye! Have a great day!");
        REPLY_MAP.put("goodbye",   "See you later! Take care!");
        REPLY_MAP.put("help",      "Commands: hi, hello, bye, help, about, hours");
        REPLY_MAP.put("about",     "I am a WhatsApp Chatbot built with Java and Spring Boot.");
        REPLY_MAP.put("hours",     "We are available 24/7!");
        REPLY_MAP.put("thanks",    "You're welcome!");
        REPLY_MAP.put("thank you", "You're welcome!");
    }
     public MessageResponse processMessage(MessageRequest request) {
        String msg = request.getMessage();
        String sender = request.getFrom();
        String now = LocalDateTime.now().format(FORMATTER);

        logger.info("=== INCOMING MESSAGE ===");
        logger.info("From    : {}", sender);
        logger.info("Message : {}", msg);
        logger.info("Time    : {}", now);

        String reply = generateReply(msg);
        logger.info("Reply   : {}", reply);
        logger.info("========================");

        return new MessageResponse(sender, reply, "sent", now);
    }

    private String generateReply(String message) {
        if (message == null || message.isBlank())
            return "Please send a message!";
        String normalized = message.trim().toLowerCase();
        if (REPLY_MAP.containsKey(normalized))
            return REPLY_MAP.get(normalized);
        for (Map.Entry<String, String> e : REPLY_MAP.entrySet())
            if (normalized.contains(e.getKey()))
                return e.getValue();
        return "I didn't understand \"" + message + "\". Try: hi, bye, or help.";
    }
}