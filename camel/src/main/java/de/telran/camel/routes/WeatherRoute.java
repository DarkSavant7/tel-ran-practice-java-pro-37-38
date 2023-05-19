package de.telran.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class WeatherRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("telegram:bots?authorizationToken={{telegram.key}}:{{telegram.token}}")
                .log(LoggingLevel.INFO, "Got tlg message ${body} from ${header.CamelTelegramChatId}")
                .choice()

                .when(simple("${body} == '/test'"))
                .log(LoggingLevel.INFO, "${header.CamelTelegramChatId} asked for some test")
                .setBody((Function<Exchange, Object>) exchange -> "Ok you tested me")
                .endChoice()

                .when(simple("${body} == '/getRequests'"))
                .log(LoggingLevel.INFO, "${header.CamelTelegramChatId} asked for requests")
                .to("sql:select * from weather_requests order by id desc limit 5")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "Got sql data ${body}")
                .endChoice()

                .otherwise()
                .log(LoggingLevel.INFO, "${header.CamelTelegramChatId} asked for weather in the city ${body}")
                .convertBodyTo(String.class)
                .to("sql:insert into weather_requests(sender, city) values (:#${header.CamelTelegramChatId}, :#${body})")
                .toD("http://api.openweathermap.org/data/2.5/weather?q=${body}&appid={{weather.token}}")
//                .toD("weather:foo?location=${body}&period=5 days&appid={{weather.token}}")
                .end()

                .to("telegram:bots?authorizationToken={{telegram.key}}:{{telegram.token}}&chatId=${header.CamelTelegramChatId}");

    }
}
//@DarkSavant