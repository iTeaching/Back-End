package org.springframework.samples.iTeaching.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

    public class Clases  {

    public static void main(String[] args) throws IOException, InterruptedException {
    var apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmFwcGVhci5pbiIsImF1ZCI6Imh0dHBzOi8vYXBpLmFwcGVhci5pbi92MSIsImV4cCI6OTAwNzE5OTI1NDc0MDk5MSwiaWF0IjoxNjQ3NjM2MjcwLCJvcmdhbml6YXRpb25JZCI6MTU1OTMxLCJqdGkiOiJkOTNmMDdlOC1hMGMxLTQ4NGYtYWYxMy02ODQ2NjZjMjNmNTYifQ.zicx46XqkxkJvZC0zqcrWf7WjzG58-OX7RXy1dZ0HwE";
    var data = Map.of(
        "endDate", "2022-03-27T11:57:00.000Z",
        "fields", Collections.singletonList("hostRoomUrl"));
        
    var request = HttpRequest.newBuilder(
                URI.create("https://api.whereby.dev/v1/meetings"))
        .header("Authorization", "Bearer " + apiKey)
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(data)))
        .build();

    var response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
    
    System.out.println("Status code: " + response.statusCode());
    System.out.println("Body: " + response.body());
    System.out.println("RoomURL: " + response.body().substring(88,149));
    }
}