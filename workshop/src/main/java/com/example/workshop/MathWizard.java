package com.example.workshop;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class MathWizard {

    @Tool(description = "This tool checks if a number is prime. It returns true if the number is prime, otherwise false.")
    public boolean isPrime(int number) {
        System.out.println("Using custom tool to check if " + number + " is prime.");
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
