package io.study.deneb;

import io.study.deneb.handler.HelloHandler;
import io.study.deneb.model.Post;
import io.study.deneb.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(PostRepository postRepository) {
    return args -> {
      postRepository.save(new Post("content1"));
      postRepository.save(new Post("content2"));
      postRepository.save(new Post("content3"));
    };
  }



}
