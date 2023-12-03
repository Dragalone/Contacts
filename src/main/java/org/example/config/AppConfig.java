package org.example.config;

import org.example.Contact;
import org.example.ContactWriter;
import org.example.Menu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.beans.BeanProperty;
import java.util.ArrayList;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("org.example")
public class AppConfig {
    @Bean
    Menu menuBean(){
        return new Menu();
    }
    @Bean
    ContactWriter contactWriterBean(){
        return new ContactWriter();
    }
}
