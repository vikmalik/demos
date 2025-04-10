package com.example.workshop;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    private final ChatClient chatClient;
    private final MediaHouseService mediaHouseService;
    private final MathWizard mathWizard;

    public DemoController(ChatClient.Builder chatClientBuilder, MediaHouseService mediaHouseService, MathWizard mathWizard) {
        this.chatClient = chatClientBuilder.build();
        this.mediaHouseService = mediaHouseService;
        this.mathWizard = mathWizard;
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @GetMapping("/demo/{userInput}")
    public String generateResponse(@PathVariable String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .tools(
                        mathWizard
                )
                .call()
                .content();
    }

    @GetMapping("/demo/articleOn/{userInput}")
    public String writeArticle(@PathVariable String userInput) {
        return mediaHouseService.getWellEditedContentOn(userInput);
    }
}

