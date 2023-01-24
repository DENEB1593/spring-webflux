package io.study.deneb.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class HelloHandler {


  public Mono<ServerResponse> hello(ServerRequest req) {
    return ok().body(Flux.just("Hello", "World!"), String.class);
  }

  public Mono<ServerResponse> stream(ServerRequest req) {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
    Flux<Map<String, Integer>> flux = Flux.fromStream(stream)
      .map(i -> Collections.singletonMap("value", i));

    return ok().contentType(MediaType.APPLICATION_NDJSON)
      .body(fromPublisher(flux, new ParameterizedTypeReference<Map<String, Integer>>(){}));
  }
}
