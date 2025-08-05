package Flight.Radar.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@Service
public class RadarService {

    private final WebClient webClient;
    private final String flightAwareApiKey;

    public RadarService(WebClient.Builder webClientBuilder,
            @Value("${flightaware.key.api}") String flightAwareApiKey) {
        this.webClient = webClientBuilder.baseUrl("https://aeroapi.flightaware.com/aeroapi").build();
        this.flightAwareApiKey = flightAwareApiKey;
    }

    public Mono<JsonNode> fetchRawFlightData() {
        // String byBox = "18.6,73.7,18.5,73.9";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/search/positions")
                        // .queryParam("by_box", byBox)
                        .queryParam("limit", 50)
                        .build())
                .header("x-apikey", this.flightAwareApiKey)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}

// Note
// current api is not working
// change the api
