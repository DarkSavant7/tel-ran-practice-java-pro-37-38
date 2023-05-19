package de.telran.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:{{spring.jms.template.task-queue}}")
                .routeId("amq_to_telegram")
                .autoStartup(true)
                .convertBodyTo(String.class)
                .to("telegram:bots?authorizationToken={{telegram.key}}:{{telegram.token}}&chatId={{telegram.daddy-token}}");
    }
}
