package com.example.workshop;

import java.util.Map;

public record HottestCityRecords(Map<Integer, Map<String, Float>> cityTemperaturePerYear) {

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Map<String, Float>> entry : cityTemperaturePerYear.entrySet()) {
            Integer year = entry.getKey();
            Map<String, Float> cityTemperatures = entry.getValue();
            result.append("Year: ").append(year).append("\n");
            for (Map.Entry<String, Float> cityEntry : cityTemperatures.entrySet()) {
                String city = cityEntry.getKey();
                Float temperature = cityEntry.getValue();
                result.append("City: ").append(city).append(", Temperature: ").append(temperature).append("\n");
            }
        }
        return result.toString();
    }
}
