package br.com.wilson.camel.camel01;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

//@Component
public class CamelConfig0 {

//    @Bean
    public RouteBuilder parallelProcessingRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Endpoint REST que recebe o payload
                rest("/api")
                        .post("/dados")
                        .consumes("application/json")
                        .type(PayloadRequest.class)
                        .to("direct:processarDados");

                // Rota principal de processamento
                from("direct:processarDados")
                        .log("Iniciando processamento de ${body.registros.size()} registros")
                        .process(exchange -> {
                            PayloadRequest payload = exchange.getIn().getBody(PayloadRequest.class);
                            exchange.getIn().setBody(payload.getRegistros());
                        })
                        // Split para dividir a coleção
                        .split(body())
                        // Parallel processing com thread pool de 4 threads
                        .parallelProcessing()
                        .threads(4)
                        .process(this::processarRegistro)
                        .to("log:processado?showAll=true&multiline=true")
                        .end();
            }

            private void processarRegistro(Exchange exchange) {
                Registro registro = exchange.getIn().getBody(Registro.class);
                // Imprime os dados do registro no console
                System.out.println("Processado: " + registro);
            }
        };
    }
}