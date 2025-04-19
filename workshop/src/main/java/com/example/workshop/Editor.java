package com.example.workshop;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Component;

@Component
public class Editor {
    private final ChatClient chatClient;

    public Editor(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(
                        ChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.8)
                                .maxTokens(2000)
                                .build()
                )
                .build();
    }

    public String review(String contentToBeReviewed) {
        return this.chatClient.prompt()
                .system("""
                        You are an exceptional editor. Provide clear and constructive feedback.
                        If you have no feedback, respond with '###No-feedback###'.
                        """
                )
                .user(contentToBeReviewed)
                .call()
                .content();
    }
}
