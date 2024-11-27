# Anotações no Spring

## Spring e suas Principais Anotações: Uma Introdução

### O Spring Framework é um dos frameworks Java mais populares e amplamente utilizados na indústria. Ele fornece um conjunto abrangente de funcionalidades que simplificam o desenvolvimento de aplicações Java, especialmente as corporativas. Uma das características mais marcantes do Spring é o uso intensivo de anotações para configurar e gerenciar a aplicação.

### Principais Anotações do Spring

* **@Component:** A anotação mais básica, indica que uma classe é um componente gerenciado pelo Spring.
  É o ponto de partida para a detecção de beans.
* **@Service:** Especialização de @Component, geralmente usada para marcar classes que representam serviços de negócios.
* **@Repository:** Outra especialização de @Component, utilizada para marcar classes que acessam o banco de dados.
  Fornece funcionalidades adicionais como tratamento de exceções de persistência.
* **@Controller:** Marca classes que atuam como controladores em aplicações web.
* **@RestController:** Combina as funcionalidades de @Controller e @ResponseBody, sendo ideal para criar APIs RESTful.
* **@Autowired:** Indica que um campo, método ou construtor precisa ser injetado com um bean do Spring.
* **@Qualifier:** Quando houver múltiplos beans do mesmo tipo, a anotação @Qualifier é usada para especificar qual bean deve ser injetado.
* **@Configuration:** Marca uma classe como uma fonte de definições de beans.
* **@Bean:** Dentro de uma classe anotada com @Configuration, marca um método que retorna um bean.
* **@Transactional:** Indica que um método deve ser executado dentro de uma transação.
* **@RequestMapping:** Mapeia requisições HTTP para métodos de controladores.

### Exemplo Prático

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
### Exemplo de @Qualifier
'''java
@Component
public class MyService {

    @Autowired
    @Qualifier("dataSourceSistemaNovo")
    private DataSource dataSource;

    // ...
}

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSourceSistemaAntigo() {
        // ...
    }

    @Bean
    public DataSource dataSourceSistemaNovo() {
        // ...
    }
}


dddd
dddd

dddd