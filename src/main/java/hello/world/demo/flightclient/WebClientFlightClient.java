package hello.world.demo.flightclient;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class WebClientFlightClient {

    private WebClient webClient;

    public WebClientFlightClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public WebClientFlightClient() {
    }

    public List<Flight> flightsFor(String symbol) {
       return webClient.get().uri("http://localhost:8080/{symbol}",symbol)
               .retrieve()
               .bodyToFlux(Flight.class)
               .doOnError(IOException.class, e -> log.error(e.getMessage()))
               .toStream().collect(Collectors.toList());
    }
}
