package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Scope("singleton")
public class Menu {
    private ArrayList<Contact> list = null;

    @Autowired
    private ContactWriter writer;
    public Menu(){
    }

    public void setList(ArrayList<Contact> list) {
        this.list = list;
    }

    private void printMenu(){
        System.out.println("1 - View the list of contacts");
        System.out.println("2 - Add a new contact");
        System.out.println("3 - Save contacts");
        System.out.println("4 - Delete contact");
        System.out.println("5 - Exit");
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while(option!=5){
            printMenu();
            try {
                option = scanner.nextInt();
                if (option > 5 || option < 1){
                    System.out.println("Please enter an integer value between 1 and 5");
                    continue;
                }
                switch (option){
                    case 1: option1(); break;
                    case 2: option2(); break;
                    case 3: option3(); break;
                    case 4: option4(); break;
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and 5");
                scanner.next();
            }
        }
    }

    private void option1(){
        if (list.isEmpty()){
            System.out.println("The contact list is empty");
            return;
        }
        for (var contact:list) {
            contact.printContact();
        }

    }

    private void option2(){
        System.out.println("Enter new contact:");
        Scanner scanner = new Scanner(System.in);
        String[] contacts = scanner.nextLine().split(";");
        boolean contactIsCorrect = false;
        if (contacts.length != 3){
            System.out.println("Enter the correct contact in the format: <Full name>;<Phone number>;<email>");
            return;
        }
        if (!validatePhone(contacts[1])){
            System.out.println("Enter the correct phone number");
            return;
        }
        if (!validateEmail(contacts[2])){
            System.out.println("Enter the correct email");
            return;
        }
        list.add(new Contact(contacts[0],contacts[1],contacts[2]));
    }

    private void option3(){
        System.out.println("Enter filename:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        writer.writeInFile(list,s);
    }

    private void option4(){
        System.out.println("Enter the email address you want to delete the contact from:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (!validateEmail(s)){
            System.out.println("Enter the correct email");
            return;
        }
        Iterator<Contact> contactIterator = list.iterator();
        int listSizeBeforeDel = list.size();
        while(contactIterator.hasNext()) {
            Contact nextContact = contactIterator.next();
            if (nextContact.getEmail().equals(s)) {
                contactIterator.remove();
            }
        }
        if (listSizeBeforeDel == list.size()){
            System.out.println("There are no matches for the entered email");
        }
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    private static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);

    private static boolean validatePhone(String phoneStr) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phoneStr);
        return matcher.matches();
    }

}
