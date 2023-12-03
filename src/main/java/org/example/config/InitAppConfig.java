package org.example.config;

import org.example.ContactReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Profile("init")
@PropertySource("classpath:application-init.properties")
@ComponentScan("org.example")
public class InitAppConfig {
    @Bean
    ContactReader contactReader(@Value("${app.default_contacts_path}") String filename){
        return new ContactReader(filename);
    }

}
