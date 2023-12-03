package org.example;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Scope("singleton")
public class ContactReader {
    private BufferedReader br;


    public ContactReader(String filename){
        File file = new File(filename);
        try {
             br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Contact> readContacts () throws IOException {
        String st;
        ArrayList<Contact> contacts = new ArrayList<>();
        while ((st = br.readLine()) != null){
            String[] s = st.split(";");
            Contact contact = new Contact(s[0],s[1],s[2]);
            contacts.add(contact);
        }
        return contacts;
    }
}

