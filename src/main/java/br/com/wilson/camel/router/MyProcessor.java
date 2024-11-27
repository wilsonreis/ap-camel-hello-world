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