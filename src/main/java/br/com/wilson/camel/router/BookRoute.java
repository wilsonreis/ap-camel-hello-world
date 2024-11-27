package br.com.wilson.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class BookRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);
        rest("/api")
                .get("/hello")
                .to("direct:hello");

        from("direct:hello")
                .log("Received request for /api/hello")
                .setBody(constant("Hello, World!"));
    }
}
