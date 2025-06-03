<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\BookRoute.java -->
package br.com.wilson.camel;

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
//http://localhost:8080/camel/api/hello

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\CamelApplication.java -->
package br.com.wilson.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelApplication.class, args);
	}

}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\camel01\CamelConfig.java -->
package br.com.wilson.camel.camel01;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelConfig extends RouteBuilder {
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
                    // Imprime o nome da requisição apenas uma vez
                    System.out.println("Processando payload com nome: " + payload.getNome());
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
        System.out.println("Processado registro: " + registro);
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\camel01\CamelConfig0.java -->
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

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\camel01\PayloadRequest.java -->
package br.com.wilson.camel.camel01;

import java.util.ArrayList;
import java.util.List;

public class PayloadRequest {
    private String nome;

    private List<Registro> registros;

    public PayloadRequest() {
        registros = new ArrayList<>();
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "PayloadRequest{" +
                "nome='" + nome + '\'' +
                ", registros=" + registros +
                '}';
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\camel01\Registro.java -->
package br.com.wilson.camel.camel01;

public class Registro {
    private String campo1;
    private String campo2;
    private String campo3;
    private String campo4;
    private String campo5;
    private String campo6;
    private String campo7;
    private String campo8;
    private String campo9;
    private String campo10;

    public Registro() {
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    public String getCampo4() {
        return campo4;
    }

    public void setCampo4(String campo4) {
        this.campo4 = campo4;
    }

    public String getCampo5() {
        return campo5;
    }

    public void setCampo5(String campo5) {
        this.campo5 = campo5;
    }

    public String getCampo6() {
        return campo6;
    }

    public void setCampo6(String campo6) {
        this.campo6 = campo6;
    }

    public String getCampo7() {
        return campo7;
    }

    public void setCampo7(String campo7) {
        this.campo7 = campo7;
    }

    public String getCampo8() {
        return campo8;
    }

    public void setCampo8(String campo8) {
        this.campo8 = campo8;
    }

    public String getCampo9() {
        return campo9;
    }

    public void setCampo9(String campo9) {
        this.campo9 = campo9;
    }

    public String getCampo10() {
        return campo10;
    }

    public void setCampo10(String campo10) {
        this.campo10 = campo10;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "campo1='" + campo1 + '\'' +
                ", campo2='" + campo2 + '\'' +
                ", campo3='" + campo3 + '\'' +
                ", campo4='" + campo4 + '\'' +
                ", campo5='" + campo5 + '\'' +
                ", campo6='" + campo6 + '\'' +
                ", campo7='" + campo7 + '\'' +
                ", campo8='" + campo8 + '\'' +
                ", campo9='" + campo9 + '\'' +
                ", campo10='" + campo10 + '\'' +
                '}';
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\camel02\CamelConfig2.java -->
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


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\camel02\ParallelResponse.java -->
package br.com.wilson.camel.camel02;

import br.com.wilson.camel.camel01.Registro;

import java.util.ArrayList;
import java.util.List;

public class ParallelResponse {
    private String nome;

    private List<Registro> registros;

    public ParallelResponse() {
        registros = new ArrayList<>();
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "PayloadRequest{" +
                "nome='" + nome + '\'' +
                ", registros=" + registros +
                '}';
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\camel03\CamelConfig3.java -->
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


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\collectors\Collector01.java -->
package br.com.wilson.camel.collectors;

import br.com.wilson.camel.variados.User;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Collector01 {
    public static void main(String[] args) {
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 40),
                new User("jessica", 30),
                new User("debora", 4)
        );
        // Create a Consumer to process each valid user
        Consumer<User> processUser = user -> {
            user.setName(user.getName().toUpperCase());
        };

        List<User> userList1 =  userList.stream()
                .filter(User::isValid)
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());


        userList1.stream().forEach(processUser);

        System.out.println(userList1);  // Saída: [2, 4, 8]

        userList1 =  userList.stream()
                .filter(User::isValid)
                .sorted(Comparator.comparingInt(User::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println(userList1);  // Saída: [2, 4, 8]
    }
}

/*
List<User> userList1 = userList.stream()
    .filter(User::isValid)
    .sorted(Comparator.comparingInt(User::getAge)
            .thenComparing(Comparator.comparingInt(User::getSalary).reversed())
            .thenComparing(User::getName))
    .collect(Collectors.toList());
 */

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\consumer\ConsumerExample01.java -->
package br.com.wilson.camel.consumer;

import br.com.wilson.camel.variados.User;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample01 {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Ana", "Pedro", "Carlos");

        // Criando um Consumer para imprimir cada nome
        Consumer<String> imprimirNome = nome -> System.out.println("Olá, " + nome + "!");

        // Aplicando o Consumer à lista
        nomes.forEach(imprimirNome);

        System.out.println("Imprimindo com utilização de Consumer!");
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 20),
                new User("jessica", 3),
                new User("debora", 4)
        );
        // Create a Consumer to process each valid user
        Consumer<User> processUser = user -> {
            user.setName(user.getName().toUpperCase());
            System.out.println(user);
        };

        userList.stream()
                .filter(User::isValid) // Filter users using the isValid method
                .forEach(processUser);   // Apply Consumer to each valid user



        Consumer<String> uppercaseConsumer = str -> System.out.println(str.toUpperCase());
        Consumer<String> lowercaseConsumer = str -> System.out.println(str.toLowerCase());

        // Concatenando dois consumidores
        Consumer<String> printBothCases = uppercaseConsumer.andThen(lowercaseConsumer);

        printBothCases.accept("Java"); // Imprime: JAVA e depois java

        System.out.println("Consumer sem print imediato, com manipulação dos dados");
        Consumer<User> manipulaDadosUsuario = user -> {
            user.setName(user.getName().toUpperCase());
        };

    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\consumer\ConsumerExample02.java -->
package br.com.wilson.camel.consumer;

import br.com.wilson.camel.variados.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample02 {
    public static void main(String[] args) {

        Consumer<User> atualizadorDeNome = pessoa -> pessoa.setName("Novo Nome");
        User pessoa = new User("Nome Antigo", 1);
        atualizadorDeNome.accept(pessoa); // Agora pessoa.getNome() é "Novo Nome"

        List<String> nomes = Arrays.asList("Ana", "Pedro", "Carlos");
        List<String> nomesPrefixados = new ArrayList<>();
        Consumer<String> adicionaPrefixo = nome -> nomesPrefixados.add("Sr(a). " + nome);
        nomes.forEach(adicionaPrefixo); // Adiciona "Sr(a). " antes de cada nome em nomesPrefixados

        // Criando um Consumer para imprimir cada nome
        Consumer<String> imprimirNome = nome -> System.out.println("Olá, " + nome + "!");

        // Aplicando o Consumer à lista
        nomes.forEach(imprimirNome);

        System.out.println("Imprimindo com utilização de Consumer!");
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 20),
                new User("jessica", 3),
                new User("debora", 4)
        );
        // Create a Consumer to process each valid user
        Consumer<User> processUser = user -> {
            user.setName(user.getName().toUpperCase());
            System.out.println(user);
        };

        userList.stream()
                .filter(User::isValid) // Filter users using the isValid method
                .forEach(processUser);   // Apply Consumer to each valid user



        Consumer<String> uppercaseConsumer = str -> System.out.println(str.toUpperCase());
        Consumer<String> lowercaseConsumer = str -> System.out.println(str.toLowerCase());

        // Concatenando dois consumidores
        Consumer<String> printBothCases = uppercaseConsumer.andThen(lowercaseConsumer);

        printBothCases.accept("Java"); // Imprime: JAVA e depois java

        System.out.println("Consumer sem print imediato, com manipulação dos dados");
        Consumer<User> manipulaDadosUsuario = user -> {
            user.setName(user.getName().toUpperCase());
        };

    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\consumer\ConsumerExample03.java -->
package br.com.wilson.camel.consumer;

import br.com.wilson.camel.variados.CamelCaseConverter;
import br.com.wilson.camel.variados.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample03 {
    public static void main(String[] args) {
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 20),
                new User("jessica", 3),
                new User("debora", 4)
        );

        List<User> userList1 = new ArrayList<>(4);

        Consumer<User> adicionaPrefixoNoNome = user -> {

            user.setName("Sr(a). " + CamelCaseConverter.toCamelCase(user.getName()));
            userList1.add(user);
            System.out.println(user);
        };

        userList.forEach(adicionaPrefixoNoNome); // Adiciona "Sr(a). " antes de cada nome em nomesPrefixados

        for (User user: userList1){
            System.out.println(user);
        }

        Consumer<User> alteraPrefixoNoNome = user -> {
            user.setName("Sr(a). " + CamelCaseConverter.toCamelCase(user.getName()));
            //System.out.println(user);
        };

        System.out.println("Sem criar nova lista");
        userList.stream().forEach(alteraPrefixoNoNome);

        System.out.println("Sem criar nova lista com isValid ");
        userList.stream().filter(User::isValid).forEach(alteraPrefixoNoNome);

    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\function\FunctionExample.java -->
package br.com.wilson.camel.function;

import java.util.List;
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // Define a função que converte String para Integer
        Function<String, Integer> stringParaInteger = s -> Integer.parseInt(s);

        // Aplica a função
        Integer resultado = stringParaInteger.apply("123");

        System.out.println(resultado);  // Saída: 123

        Function<String, Boolean> stringToBoolean = s -> Boolean.valueOf(s);

        Boolean b = stringToBoolean.apply("true");
        System.out.println(b);

        Function<Integer, Integer> dobrar = x -> x * 2;
        Function<Integer, Integer> quadrado = x -> x * x;

        resultado = dobrar.apply(5); // resultado = 10
        System.out.println("resultado1 : "+ resultado);
        resultado = quadrado.apply(3); // resultado = 9
        System.out.println("resultado2 : "+ resultado);


        Function<String, String> paraMaiusculas = s -> s.toUpperCase();
        Function<String, String> inverter = s -> new StringBuilder(s).reverse().toString();

        String texto = "Olá, mundo!";
        String textoMaiusculo = paraMaiusculas.apply(texto);
        System.out.println("textoMaiusculo : " + textoMaiusculo);
        String textoInvertido = inverter.apply(texto);
        System.out.println("textoInvertido : " + textoInvertido);

        Function<List<Integer>, Integer> somarElementos = lista -> lista.stream().reduce(0, Integer::sum);

        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        int soma = somarElementos.apply(numeros);
        System.out.println("soma1 : " + soma);

        Function<List<Integer>, Integer> somarElementos2 = lista -> lista.stream().reduce(0, (a, c) -> a+c);
        soma = somarElementos2.apply(numeros);
        System.out.println("soma2 : " + soma);

    }
}
/**
Dicas para Aprofundar:
Pratique: Crie seus próprios exemplos e experimente diferentes combinações de funções.
Explore outras interfaces funcionais:
 Além de Function, existem outras como Predicate, Consumer, Supplier, etc.
Entenda o conceito de currying: Uma técnica de transformação de funções que permite aplicar argumentos parcialmente.
Utilize bibliotecas funcionais: Java 8 introduziu streams e outras funcionalidades funcionais, e existem bibliotecas de terceiros que oferecem ainda mais recursos.
 **/

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExample.java -->
package br.com.wilson.camel.lambda01;

public class LambdaExample {
    public static void main(String[] args) {
        // Interface funcional para verificar se um número é positivo
        VerificarNumero verificar = n -> n > 0;

        System.out.println(verificar.checar(5));   // Saída: true
        System.out.println(verificar.checar(-3));  // Saída: false
    }
}

@FunctionalInterface
interface VerificarNumero {
    boolean checar(int n);  // Método único da interface funcional
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExample01.java -->
package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;

public class LambdaExample01 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // Com lambda
        numeros.forEach(n -> System.out.println(n));
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExample02.java -->
package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExample02 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Filtra números pares
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0) // Lambda que retorna true para números pares
                .collect(Collectors.toList());

        System.out.println(pares);  // Saída: [2, 4, 6]
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExample03.java -->
package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExample03 {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "lambda", "stream");

        // Transforma em maiúsculas
        List<String> palavrasMaiusculas = palavras.stream()
                .map(s -> s.toUpperCase()) // Lambda que transforma a string para maiúsculas
                .collect(Collectors.toList());

        System.out.println(palavrasMaiusculas);  // Saída: [JAVA, LAMBDA, STREAM]

        palavras.stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExample04.java -->
package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaExample04 {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Maria", "Ana", "Carlos");

        // Ordena por ordem alfabética inversa usando lambda
        Collections.sort(nomes, (a, b) -> b.compareTo(a));

        System.out.println(nomes);  // Saída: [Maria, Carlos, Ana]
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExample07.java -->
package br.com.wilson.camel.lambda01;

import java.util.function.BiFunction;

public class LambdaExample07 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> soma = (a, b) -> a + b;

        System.out.println(soma.apply(3, 7));  // Saída: 10
    }
}



<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExample08.java -->
package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;


public class LambdaExample08 {
        public static void main(String[] args) {
            List<String> palavras = Arrays.asList("banana", "maçã", "uva");

            // Ordena palavras por tamanho usando um lambda
            palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

            System.out.println(palavras);  // Saída: [uva, maçã, banana]
        }
    }


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda01\LambdaExampleMetodo.java -->
package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExampleMetodo {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // Com lambda
        numeros.forEach(getIntegerConsumer());
    }

    private static Consumer<Integer> getIntegerConsumer() {
        return n -> {
            n = n * 3;
            System.out.println(n);
        };
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda02\CountingExample.java -->
package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;

public class CountingExample {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "parallel", "lambda", "collector");

        long count = palavras.stream()
                .filter(palavra -> palavra.length() > 5) // Filtra palavras com mais de 5 letras
                .count();

        System.out.println(count);  // Saída: 3
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda02\FlatMapExample.java -->
package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<Integer>> listaDeListas = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        // Unindo todas as listas em uma única lista
        List<Integer> resultado = listaDeListas.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(resultado);  // Saída: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda02\GroupingExample.java -->
package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingExample {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("apple", "apricot", "banana", "blueberry", "cherry");

        // Agrupa as palavras pela primeira letra
        Map<Character, List<String>> agrupadoPorLetra = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra.charAt(0)));

        System.out.println(agrupadoPorLetra);
        // Saída: {a=[apple, apricot], b=[banana, blueberry], c=[cherry]}
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda02\ParallelStreamExample.java -->
package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // Calcula o quadrado de cada número em paralelo
        List<Integer> quadrados = numeros.parallelStream()
                .map(n -> n * n) // Calcula o quadrado
                .collect(Collectors.toList());

        System.out.println(quadrados);  // Saída: [1, 4, 9, 16, 25]
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda02\StreamExample01.java -->
package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample01 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(5, 3, 8, 1, 9, 2, 4, 7);

        // Filtra números pares, ordena e transforma em lista
        List<Integer> paresOrdenados = numeros.stream()
                .filter(n -> n % 2 == 0)  // Filtra números pares
                .sorted()                 // Ordena os números
                .collect(Collectors.toList());

        System.out.println(paresOrdenados);  // Saída: [2, 4, 8]
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\lambda02\StreamExample02.java -->
package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;

public class StreamExample02 {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "api", "parallel", "mais_um");

        // Transforma em maiúsculas e concatena
        String resultado = palavras.stream()
                .map(String::toUpperCase) // Transforma cada string em maiúscula
                .reduce("", (a,b) -> a + b + " "); // Concatena com espaço entre palavras

        System.out.println(resultado);  // Saída: "JAVA STREAM API PARALLEL "
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\predicate\PredicateExample.java -->
package br.com.wilson.camel.predicate;

import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<Integer> isEven = number -> number % 2 == 0; //par

        System.out.println(isEven.test(4)); // true
        System.out.println(isEven.test(5)); // false
    }
}

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\predicate\PredicatestartsWithA.java -->
package br.com.wilson.camel.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicatestartsWithA {
    public static void main(String[] args) {
        List<String> paises = List.of("Brasil", "França", "Alemanha", "Portugal");
        Predicate<String> startsWithA = s -> s.startsWith("A");
        List<String> listStartsWithA = paises.stream().
                filter(startsWithA).
                collect(Collectors.toList());
        System.out.println("listStartsWithA : " + listStartsWithA);
    }

    private static Predicate<String> getA() {
        return s -> isA(s);
    }

    private static boolean isA(String s) {
        return s.startsWith("A");
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\predicate\PredicateWithStream.java -->
package br.com.wilson.camel.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateWithStream {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Predicate<Integer> isGreaterThanThree = number -> number > 3;

        List<Integer> filteredNumbers = numbers.stream()
                .filter(isGreaterThanThree)
                .collect(Collectors.toList());

        System.out.println(filteredNumbers); // [4, 5, 6]
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\predicate\Variados.java -->
package br.com.wilson.camel.predicate;

public class Variados {

}
/*
Predicate<Integer> isEvenAndGreaterThan10 = number -> number % 2 == 0 && number > 10;
Predicate<String> startsWithAOrB = s -> s.startsWith("A") || s.startsWith("B");

Interface	Descrição	Exemplo
Predicate	Verifica se um valor satisfaz uma condição	number -> number > 0
Consumer	Consome um valor, realizando alguma ação	System.out::println
Function	Transforma um valor em outro	s -> s.toUpperCase()
Supplier	Fornece um valor	() -> new Random()
 */


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\router\BookRoute2.java -->
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

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\router\EIPSplitRouter.java -->
package br.com.wilson.camel.router;

import org.apache.camel.builder.RouteBuilder;

public class EIPSplitRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\router\MyBean.java -->
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.wilson.camel.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBean {

    private static final Logger LOG = LoggerFactory.getLogger(MyBean.class);

    public MyBean() {
        // force slow startup
        try {
            LOG.info("Forcing 2 sec delay to have slow startup");
            Thread.sleep(2000);
        } catch (Exception e) {
            // ignore
        }
    }

    public String hello() {
        return "Hello how are you?";
    }

}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\router\MyProcessor.java -->
package br.com.wilson.camel.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import org.apache.camel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

@Service
public class MyProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(MyProcessor.class);
    public void process(Exchange exchange) {
        Message in = exchange.getIn();
        in.setBody(in.getBody(String.class) + " World!");
    }
}

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\router\MyRouteBuilder.java -->
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.wilson.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=100000")
            .to("log:fast?level=OFF")
            .to("direct:slow")
            .log("${body}");

        from("direct:slow")
            .to("log:slow?level=OFF")
            .bean(MyBean.class, "hello");
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\router\Pedido.java -->
package br.com.wilson.camel.router;

import java.io.Serializable;

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String tipo; // Tipo do pedido (ex: "ELETRONICO", "ROUPA")
    private String descricao;
    private double valor;

    // Construtor padrão
    public Pedido() {
    }

    // Construtor com parâmetros
    public Pedido(String id, String tipo, String descricao, double valor) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\router\PedidoRouteBuilder.java -->
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


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\variados\CamelCaseConverter.java -->
package br.com.wilson.camel.variados;

public class CamelCaseConverter {

    public static String toCamelCase(String texto) {
        StringBuilder camelCase = new StringBuilder();
        boolean nextUpperCase = false;

        for (char c : texto.toCharArray()) {
            if (Character.isWhitespace(c)) {
                nextUpperCase = true;
            } else {
                if (nextUpperCase || camelCase.length() == 0) {
                    camelCase.append(Character.toUpperCase(c));
                } else {
                    camelCase.append(c);
                }
                nextUpperCase = false;
            }
        }

        return camelCase.toString();
    }

    public static void main(String[] args) {
        String texto = "hello world";
        String textoCamelCase = toCamelCase(texto);
        System.out.println(textoCamelCase); // Imprime: HelloWorld
    }
}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\variados\User.java -->
package br.com.wilson.camel.variados;

import java.util.Objects;
import java.util.function.Predicate;

public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public boolean isValid() {
        Predicate<User> isAdult = user -> user.getAge() >= 18;
        Predicate<User> hasValidName = user -> user.getName() != null && !user.getName().isEmpty();
        return isAdult.and(hasValidName).test(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public int compareTo(User other) {
        return Integer.compare(this.age, other.age);
    }

}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\variados\UtilizandoUser.java -->
package br.com.wilson.camel.variados;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class UtilizandoUser {

    static User user01;
    static User user02;
    static User user03;
    static User user04;
    static Map<String, User> userMap = new HashMap<>();

    public static void main(String[] args) {
        user01 = new User("wilson", 1);
        user02 = new User("wagner", 20);
        user03 = new User("jessica", 3);
        user04 = new User("debora", 4);

        // Add users to Map with unique keys (assuming usernames are unique)
        userMap.put(user01.getName(), user01);
        userMap.put(user02.getName(), user02);
        userMap.put(user03.getName(), user03);
        userMap.put(user04.getName(), user04);

        System.out.println("Todos os usuários:");
        for (User user : userMap.values()) {
            System.out.println(user);
        }

        // Filtering by Predicate with forEach (might need adjustments depending on your use case)
        System.out.println("\nUsuários adultos:");
        //Predicate<User> isAdult = user -> user.getAge() >= 18;
        System.out.println("Imprimindo a coleção de válidos");
        userMap.values().stream().filter(User::isValid).forEach(System.out::println);

        // Filtering by key using getOrDefault (assuming usernames are unique)
        System.out.println("\nUsuário 'wagner':");
        User adultUser = userMap.getOrDefault("wagner", null);
        if (adultUser != null) {
            System.out.println(adultUser);
        } else {
            System.out.println("Usuário não encontrado");
        }
        userMap.values().stream().filter(User::isValid).forEach(System.out::println);
    }
}

<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\variados\UtilizandoUser01.java -->
package br.com.wilson.camel.variados;

import java.util.ArrayList;
import java.util.List;

public class UtilizandoUser01 {
    static User user01;
    static User user02;
    static User user03;
    static User user04;
    static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        user01 = new User("wilson", 1);
        user02 = new User("wagner", 20);
        user03 = new User("jessica", 3);
        user04 = new User("debora", 4);
        userList.add(user01);
        userList.add(user02);
        userList.add(user03);
        userList.add(user04);

        System.out.println("Todos os usuários:");
        for (User user : userList) {
            System.out.println(user);
        }

        // Imprimindo usuários adultos
        System.out.println("\nUsuários adultos:");
        userList.stream().filter(User::isValid).forEach(System.out::println);
    }

}


<!-- C:\w\p\camel\ap-camel-hello-world\src\main\java\br\com\wilson\camel\variados\UtilizandoUser02.java -->
package br.com.wilson.camel.variados;

import java.util.ArrayList;
import java.util.List;

public class UtilizandoUser02 {

    public static void main(String[] args) {
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 20),
                new User("jessica", 3),
                new User("debora", 4)
        );

        System.out.println("Todos os usuários:");
        for (User user : userList) {
            System.out.println(user);
        }

        // Imprimindo usuários adultos
        System.out.println("\nUsuários adultos:");
        userList.stream().filter(User::isValid).forEach(System.out::println);
    }

}


