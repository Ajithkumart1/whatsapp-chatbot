package com.chatbot.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {
    @JsonProperty("from")
    private String from;
    @JsonProperty("message")
    private String message;
    @JsonProperty("timestamp")
    private String timestamp;

    public MessageRequest() {}

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}