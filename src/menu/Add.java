package menu;

import contacts.ContactsManager;
import contacts.PhoneFormat;
import input.Input;
import java.util.Arrays;

public class Add extends MenuItem {

    public Add() {
        setDescription("Add a contact");
    }

    @Override
    public void action() {
        String name = Input.getString("Enter the contact's name").trim();
        String phone, newPhone;
        do {
            phone = Input.getString("Enter the phone number").trim();
            newPhone = PhoneFormat.onlyNumbers(phone);

            if(!ContactsManager.isValidPhoneNumber(newPhone)){
                System.out.println("The phone number must follow:");
                for(PhoneFormat format: ContactsManager.PHONE_LENGTHS){
                    System.out.println(format.getFormat() + " " + format.getLength());
                }
            } else {
                break;
            }
        } while(true);

        String formattedNumber = ContactsManager.formatPhoneNumber(newPhone);

        if(!ContactsManager.hasContact(name)) {
            ContactsManager.addData(Arrays.asList(name, formattedNumber));
            ContactsManager.saveData();
            System.out.println("\nAdded " + name + " to the contact list\n");
        } else {
            String confirm = Input.getString(
                    String.format("There is already a contact with the name %s, do you want to overwrite it?\n[Y/N]", name)
            );
            System.out.println();
            if(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                ContactsManager.addData(Arrays.asList(name, formattedNumber));
            } else {
                System.out.println("\"Add contact\" cancelled ...\n");
            }
        }
    }

    @Override
    public boolean isAvailable() {
        return ContactsManager.numberOfContacts() < Integer.MAX_VALUE;
    }
}