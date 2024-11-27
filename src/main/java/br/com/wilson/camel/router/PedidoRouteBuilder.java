package br.com.wilson.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class PedidoRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        // Configuração REST para JSON
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

        // Definição do endpoint REST
        rest("/api/pedidos")
                .post()
                .type(Pedido.class) // Classe do objeto que representa o pedido
                .to("direct:processarPedido");

        // Rota de processamento principal
        from("direct:processarPedido")
                .log("Processando pedido: ${body}")
                .choice()
                .when(simple("${body.tipo} == 'ELETRONICO'"))
                .to("direct:eletronico")
                .when(simple("${body.tipo} == 'ROUPA'"))
                .to("direct:roupa")
                .otherwise()
                .to("direct:geral");

        // Rota para pedidos de eletrônicos
        from("direct:eletronico")
                .log("Processando pedido de ELETRONICO")
                .to("log:pedidoEletronico?level=INFO");

        // Rota para pedidos de roupa
        from("direct:roupa")
                .log("Processando pedido de ROUPA")
                .to("log:pedidoRoupa?level=INFO");

        // Rota para pedidos gerais
        from("direct:geral")
                .log("Processando pedido GERAL")
                .to("log:pedidoGeral?level=INFO");
    }
}
