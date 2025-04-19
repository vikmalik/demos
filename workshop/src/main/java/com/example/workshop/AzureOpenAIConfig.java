package com.example.workshop;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import org.springframework.ai.model.azure.openai.autoconfigure.AzureOpenAIClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureOpenAIConfig {

    @Bean
    public AzureOpenAIClientBuilderCustomizer customAzureOpenAIClientBuilder() {
        return openAiClientBuilder -> {
            openAiClientBuilder
                    .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY));;
        };
    }
}

