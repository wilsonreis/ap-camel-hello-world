package br.com.wilson.camel.camel02;

import br.com.wilson.camel.camel01.PayloadRequest;
import br.com.wilson.camel.camel01.Registro;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelConfig2 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Endpoint REST que recebe o payload
        rest("/api")
                .post("/dados2")
                .consumes("application/json")
                .type(PayloadRequest.class)
                .outType(ParallelResponse.class)
                .to("direct:processarDados2");

        // Rota principal de processamento
        from("direct:processarDados2")
                .log("Iniciando processamento de ${body.registros.size()} registros")
                .process(exchange -> {
                    PayloadRequest payload = exchange.getIn().getBody(PayloadRequest.class);
                    ParallelResponse response = new ParallelResponse();
                    response.setNome(payload.getNome());
                    response.setRegistros(payload.getRegistros());
                    exchange.getIn().setBody(response);
                })
                // Split para dividir a coleção
                .split(body())
                // Parallel processing com thread pool de 4 threads
                .parallelProcessing()
                .threads(4)
                .process(this::processarRegistro)
                .end();
    }

    private void processarRegistro(Exchange exchange) {
        Registro registro = exchange.getIn().getBody(Registro.class);
        // Nenhuma impressão no console nesta etapa
    }
}
