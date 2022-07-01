package hello.world.demo.flightclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class WebClientFlightClientTest {
    WebClient webClient = WebClient.builder().build();
    @Test
    void shouldRetrieveFlightsFromService() {
        WebClientFlightClient webClientFlightClient = new WebClientFlightClient(webClient);
        webClientFlightClient.flightsFor("flight");

        List<Flight> flights= webClientFlightClient.flightsFor("flight");

        Assertions.assertNotNull(flights);
        List<Flight> mockList = flights.stream().limit(30).collect(Collectors.toList());

    }
    }
