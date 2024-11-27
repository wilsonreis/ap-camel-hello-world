package br.com.wilson.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class BookRoute2 extends RouteBuilder {

    @Autowired
    MyProcessor myProcessor;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.off)  // Isso já está correto
                .enableCORS(true);

        rest("/api")
                .consumes(MediaType.TEXT_PLAIN_VALUE)
                .produces(MediaType.TEXT_PLAIN_VALUE)
                .get("/hello2")
                .bindingMode(RestBindingMode.off)
                .type(String.class)  // Especifica o tipo de entrada
                .outType(String.class)  // Especifica o tipo de saída
                .to("direct:hello2");

        from("direct:hello2")
                .log("Received request for /api/hello2")
                .process(myProcessor)
                .end();
    }
}