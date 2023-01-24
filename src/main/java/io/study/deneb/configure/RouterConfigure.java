package io.study.deneb.configure;

import io.study.deneb.handler.HelloHandler;
import io.study.deneb.handler.PostHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Flux.just;

@Configuration
public class RouterConfigure {

  private final HelloHandler helloHandler;
  private final PostHandler postHandler;

  public RouterConfigure(HelloHandler helloHandler,
                         PostHandler postHandler) {
    this.helloHandler = helloHandler;
    this.postHandler = postHandler;
  }

  @Bean
  public RouterFunction<ServerResponse> routes() {
    return route(GET("/"), helloHandler::hello)
      .andRoute(GET("/test"), request -> ok().body(just("Hello World"), String.class))
      .andRoute(GET("/stream"), helloHandler::stream)
      .andRoute(POST("/post"), postHandler::save);
  }


}
