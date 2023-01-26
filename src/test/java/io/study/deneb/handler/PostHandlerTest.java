package io.study.deneb.handler;

import io.study.deneb.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient // For WebFlux Support
class PostHandlerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldReturnSavedPost() throws Exception {
    Post post = new Post("test content");

    webTestClient.post().uri("/post")
      .contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(post), Post.class)
      .exchange()
      .expectStatus().isOk()
      .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.content").isEqualTo(post.getContent());
  }

  @Test
  void shouldReturnPostList() {
    webTestClient.get().uri("/post")
      .exchange()
      .expectStatus().isOk()
      .expectBody()
        .jsonPath("$.size()").isEqualTo(3)
        .jsonPath("$[?(@.id == 1)].content").isEqualTo("content1")
        .jsonPath("$[?(@.id == 2)].content").isEqualTo("content2")
        .jsonPath("$[?(@.id == 3)].content").isEqualTo("content3");
  }


}