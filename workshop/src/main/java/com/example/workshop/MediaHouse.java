package com.example.workshop;

import org.springframework.stereotype.Service;

@Service
public class MediaHouse {

    private final Reporter reporter;
    private final Editor editor;

    private final Integer MAX_ITERATION = 2;

    public MediaHouse(Reporter reporter, Editor editor) {
        this.reporter = reporter;
        this.editor = editor;
    }


    public String writeAnArticleOn(String topic) {
        System.out.println("Generating well-edited content on " + topic);

        String content = reporter.generate(topic);
        System.out.printf("\nReporter Generated Content: %s\n", content);
        int numberOfIterations = 0;
        do {
            String review = editor.review(content);
            System.out.printf("\nEditor's review comments: %s\n", review);

            if (review.contains("###No-feedback###")) {
                break;
            } else {
                content = reporter.generate(review);
                System.out.printf("\nReporter Generated Content: %s\n", content);
                numberOfIterations++;
            }
        } while (numberOfIterations < MAX_ITERATION);
        return content;
    }
}
