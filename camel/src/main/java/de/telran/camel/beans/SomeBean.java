package de.telran.camel.beans;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SomeBean {
    public void doSomething(Exchange exchange) {

        log.info("Hello from bean! Got message {}", exchange.getMessage());
    }
}
