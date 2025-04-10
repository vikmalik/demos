package com.example.workshop;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class MediaHouseService {

    private final Reporter reporter;
    private final Editor editor;
    private final Integer MAX_ITERATION = 2;

    public MediaHouseService(Reporter reporter, Editor editor) {
        this.reporter = reporter;
        this.editor = editor;
    }

    public String getWellEditedContentOn(String subject) {
        System.out.println("Generating well-edited content on " + subject);

        int counter = 0;
        String content = reporter.generate(subject);
        System.out.printf("\nReporter Generated Content: %s\n", content);
        do {
            String review = editor.review(content);
            System.out.printf("\nEditor's review comments: %s\n", review);
            if(review.contains("###No-feedback###")){
                break;
            } else {
                content = reporter.generate(review);
                System.out.printf("\nReporter Generated Content: %s\n", content);
            }
            counter++;
        } while (counter < MAX_ITERATION );
        return content;
    }
}
