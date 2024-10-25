package br.com.wilson.camel;

import org.apache.camel.Message;
import org.apache.camel.ValidationException;
import org.apache.camel.spi.DataType;
import org.apache.camel.spi.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("greetingValidator")
public class MyValidator extends Validator {

    private static final Logger LOG = LoggerFactory.getLogger(MyValidator.class);

    @Value("${greeting}")
    private String greeting;

    @Override
    public void validate(Message message, DataType type) throws ValidationException {
        Object body = message.getBody();
        LOG.info("Validating : [{}]", body);

        if (body instanceof String && body.equals(greeting)) {
            LOG.info("OK");
        } else {
            throw new ValidationException(
                    message.getExchange(),
                    String.format("Conteúdo inválido. Esperado: '%s', Recebido: '%s'", greeting, body)
            );
        }
    }
}