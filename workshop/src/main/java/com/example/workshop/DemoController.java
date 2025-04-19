package com.example.workshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;

import java.util.List;

@RestController
public class DemoController {

    private final ChatClient chatClient;
    private final MathWizard mathWizard;
    private final MediaHouse mediaHouse;

    public DemoController(ChatClient.Builder chatClientBuilder, MathWizard mathWizard, MediaHouse mediaHouse) {
        this.chatClient = chatClientBuilder.build();
        this.mathWizard = mathWizard;
        this.mediaHouse = mediaHouse;
    }

    @GetMapping("/demo/{userInput}")
    public String demo(@PathVariable String userInput) {
        return this.chatClient
                .prompt()
                .user(userInput)
                .tools(mathWizard)
                .call()
                .content();
    }

    @GetMapping("/demo/write_article_on/{userInput}")
    public String writeArticleOn(@PathVariable String userInput) {
        return mediaHouse.writeAnArticleOn(userInput);
    }

    @GetMapping("/demo/hottest_city_in/{country}")
    public String hottest_city_in(@PathVariable String country) {
        HottestCityRecords hottestCityRecords = this.chatClient
                .prompt()
                .user(String.format("""
                        Provide a list of the 5 hottest cities in %s over the past 10 years,
                        along with their respective temperatures. Format the response as a JSON object.
                        
                        ### Example ###
                        {{
                            "Year": 2023,
                            "Hottest Cities": {{
                                "City1": 45.0,
                                "City2": 44.5
                            }}
                        }}
                        """, country))
                .call()
                .entity(HottestCityRecords.class);

        return hottestCityRecords.toString();
    }
}
