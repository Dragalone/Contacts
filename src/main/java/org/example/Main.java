package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Environment env = ctx.getEnvironment();
        ArrayList<Contact> list = new ArrayList<>();
        if (env.getActiveProfiles()[0].equals("init")){
            ContactReader reader = ctx.getBean(ContactReader.class);
            try {
                list = reader.readContacts();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        Menu menu = ctx.getBean(Menu.class);
        menu.setList(list);
        menu.start();
    }
}