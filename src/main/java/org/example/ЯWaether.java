package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ЯWaether {

    public static void main(String[] args) throws IOException {

        String access_key = "85111a7d-56cd-4aa7-9470-1fda149798c5";
        String path = "https://api.weather.yandex.ru/v2/forecast";
        final double lat = 51.45;
        final double lon = 37.36;
        final int limit = 7;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path+ "?lat=" + lat + "&lon=" + lon + "&limit="+ limit))
                .headers("X-Yandex-Weather-Key", access_key)
                .headers("Content-Type", "application/json")
                .GET()
                .build();
        System.out.println(request);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            String jsonString = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            // Преобразование строки в JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            // Извлечение значения по ключу "temp"
            String temp = jsonNode.get("fact").get("temp").asText();
            System.out.println("Temp: " + temp);

            int sum = Integer.parseInt(temp);
            for (int i = 1; i < limit; i++){
                int currentTemp = jsonNode.get("forecasts").get(i).get("parts").get("day").get("temp_avg").asInt();
                System.out.print(currentTemp + ", ");
                sum += currentTemp;
            }
            System.out.println();
            System.out.println("Средняя температура за " + limit + " дней: "+ sum/limit);


        } catch (Exception e) {
            System.err.println("Error making HTTP request: " + e.getMessage());
        }




    }
}
