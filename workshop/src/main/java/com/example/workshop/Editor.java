package com.example.workshop;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
import org.springframework.ai.chat.prompt.ChatOptions;

@Component
public class Editor {

    private final ChatClient chatClient;

    public Editor(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(
                        ChatOptions.builder()
                                .model("gpt-4o-mini")
                                .temperature(0.8)
                                .build()
                )
                .build();
    }

    public String review(String contentToBeReviewed) {
        return this.chatClient.prompt()
                .system("""
                        You are an exceptional editor. Provide clear and constructive feedback.
                        """
                )
                .user(contentToBeReviewed)
                .call()
                .content();
    }

}
