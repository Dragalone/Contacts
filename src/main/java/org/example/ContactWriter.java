package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import java.io.*;
import java.util.ArrayList;

@Scope("singleton")
public class ContactWriter {
    @Value("${app.contacts_path}")
    private String contactsPath;
    public void writeInFile(ArrayList<Contact> list,String filename){
        filename = contactsPath + filename;
        PrintWriter writer;
        try {
             writer = new PrintWriter(filename, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        for (var contact:list) {
            writer.println(contact.getFullName() + contact.getPhoneNumber() + contact.getEmail());
        }
        writer.close();
        System.out.println("The data has been saved");
    }

}
