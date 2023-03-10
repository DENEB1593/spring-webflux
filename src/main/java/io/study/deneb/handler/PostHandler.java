package io.study.deneb.handler;

import io.study.deneb.model.Post;
import io.study.deneb.repository.PostRepository;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.BodyInserters.fromServerSentEvents;

@Component
public class PostHandler {

  private final PostRepository postRepository;

  public PostHandler(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    Mono<Post> saved = request.bodyToMono(Post.class)
      .doOnNext(postRepository::save);

    return ServerResponse.ok().body(saved, Post.class);
  }

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok()
      .bodyValue(postRepository.findAll());
  }

  public Mono<ServerResponse> count(ServerRequest request) {
    return ServerResponse.ok()
      .contentType(MediaType.TEXT_EVENT_STREAM)
      .body(
        fromServerSentEvents(Flux.interval(Duration.ofSeconds(1))
        .map(time -> ServerSentEvent.<Long>builder()
          .id(String.valueOf(time))
          .event("postcount-event")
          .data(postRepository.count())
          .build()
        )
        )
      );
  }
}
