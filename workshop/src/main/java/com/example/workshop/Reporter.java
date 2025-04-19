package com.example.workshop;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Component;

@Component
public class Reporter {
    private final ChatClient chatClient;

    public Reporter(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(
                        ChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.1)
                                .maxTokens(1000)
                                .build()
                )
                .build();
    }

    public String generate(String userInput) {
        return this.chatClient.prompt()
                .system("""
                        You are a skilled journalist known for creating engaging and informative articles.
                        Write a concise and captivating article with a maximum of 200 words.
                        Ensure the content is well-structured and incorporates relevant feedback for improvement.
                        """)
                .user(userInput)
                .call()
                .content();
    }
}
