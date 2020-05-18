package macan.marko.usermanagement.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("user-management-exchange");
    }
}