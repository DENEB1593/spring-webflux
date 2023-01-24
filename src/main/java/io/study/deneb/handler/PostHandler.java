package io.study.deneb.handler;

import io.study.deneb.model.Post;
import io.study.deneb.repository.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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

}
