package com.example.workshop;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.policy.AddHeadersPolicy;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpPipelinePolicy;
import org.springframework.ai.autoconfigure.azure.openai.AzureOpenAIClientBuilderCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AzureOpenAiConfig {

    @Value("${spring.ai.azure.openai.api-key}")
    private String apiKey;

    @Bean
    public AzureOpenAIClientBuilderCustomizer customAzureOpenAIClientBuilder() {

        return openAiClientBuilder -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");
            headers.set("x-cisco-app", "my-app");

            List<HttpPipelinePolicy> policies = new ArrayList<>();
            policies.add(new AddHeadersPolicy(headers));

            openAiClientBuilder
                    .addPolicy(new AddHeadersPolicy(headers))
                    .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS));;
        };
    }

}