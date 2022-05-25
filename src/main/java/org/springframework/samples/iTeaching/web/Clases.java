package org.springframework.samples.iTeaching.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Clases {
	
	public static String url() throws IOException, InterruptedException {
		var apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmFwcGVhci5pbiIsImF1ZCI6Imh0dHBzOi8vYXBpLmFwcGVhci5pbi92MSIsImV4cCI6OTAwNzE5OTI1NDc0MDk5MSwiaWF0IjoxNjUzNDk4MTY0LCJvcmdhbml6YXRpb25JZCI6MTYxMTYyLCJqdGkiOiIxMTE0M2QyMS1jOGY3LTQ0NzEtYmVkMC04NGMyZTE5MTBmN2UifQ.J-F8ud4aCoqS0UnUNWCbXMpZlSqGTIHx296HkdOW0M8";
		var data = Map.of("endDate", "2022-10-28T11:57:00.000Z", "fields", Collections.singletonList("hostRoomUrl"));

		var request = HttpRequest.newBuilder(URI.create("https://api.whereby.dev/v1/meetings"))
				.header("Authorization", "Bearer " + apiKey).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(data))).build();

		var response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		
		
		
		System.out.println("Status code: " + response.statusCode());
		System.out.println("Body: " + response.body());
		System.out.println("RoomURL: " + response.body().substring(88, 153));
		return response.body().substring(88, 153);
	}

}