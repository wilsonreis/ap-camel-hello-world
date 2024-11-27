package br.com.wilson.camel.camel03;

import br.com.wilson.camel.camel01.PayloadRequest;
import br.com.wilson.camel.camel01.Registro;
import br.com.wilson.camel.camel02.ParallelResponse;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CamelConfig3 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Endpoint REST que recebe o payload
        rest("/api")
                .post("/dados3")
                .consumes("application/json")
                .type(PayloadRequest.class)
                .outType(ParallelResponse.class)
                .to("direct:processarDados3");

        // Rota principal de processamento
        from("direct:processarDados3")
                .log("Iniciando processamento de ${body.registros.size()} registros")
                .process(exchange -> {
                    PayloadRequest payload = exchange.getIn().getBody(PayloadRequest.class);
                    List<Registro> registros = payload.getRegistros();

                    // Processa cada registro em paralelo
                    List<Registro> registrosModificados = registros.parallelStream()
                            .map(this::transformarParaMaiusculo)
                            .collect(Collectors.toList());

                    // Define o response com registros modificados
                    ParallelResponse response = new ParallelResponse();
                    response.setNome(payload.getNome());
                    response.setRegistros(registrosModificados);
                    exchange.getIn().setBody(response);
                });
    }

    private Registro transformarParaMaiusculo(Registro registro) {
        registro.setCampo1(registro.getCampo1().toUpperCase());
        registro.setCampo2(registro.getCampo2().toUpperCase());
        registro.setCampo3(registro.getCampo3().toUpperCase());
        registro.setCampo4(registro.getCampo4().toUpperCase());
        return registro;
    }
}
