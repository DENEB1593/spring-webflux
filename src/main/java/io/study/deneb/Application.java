package io.study.deneb;

import io.study.deneb.handler.HelloHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

//  @Bean
//  public RouterFunction<ServerResponse> routes(HelloHandler helloHandler) {
//    return route(GET("/"), helloHandler::hello)
//      .andRoute(GET("/stream"), helloHandler::stream);
//  }


}
