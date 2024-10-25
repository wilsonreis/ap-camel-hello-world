package br.com.wilson.camel;


import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookRoute3 extends RouteBuilder {

    @Autowired
    MyProcesso myProcessor;

    @Override
    public void configure() throws Exception {
        // Define o tratamento de exceção global
        onException(ValidationException.class)
                .handled(true)
                .setHeader("Content-Type", constant("text/plain"))
                .setHeader("HTTP_RESPONSE_CODE", constant(400))
                .transform().simple("Erro de validação: ${exception.message}");

        validator()
                .type("greeting")
                .withBean("greetingValidator");

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.off);

        rest("/api")
                .produces("text/plain")
                .consumes("text/plain")
                .get("/hello3")
                .to("direct:hello3");

        from("direct:hello3")
                .log("Received request for /api/hello")
                .outputTypeWithValidate("greeting")
                .process(myProcessor)
                .end();
    }
}