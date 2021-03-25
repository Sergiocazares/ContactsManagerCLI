package menu;

import contacts.ContactsManager;
import input.Input;

public class Delete extends MenuItem {

    public Delete() {
        setDescription("Delete a contact");
    }

    @Override
    public void action() {

        ContactsManager.printContacts();

        String name = Input.getString("Name of the contact to delete");

        if (ContactsManager.hasContact(name)) {
            ContactsManager.deleteContact(name);
            ContactsManager.saveData();
            System.out.println("\n" + name + " successfully deleted from list\n");
        } else {
            System.out.println("\n" + name + " doesn't exist");
        }
    }

    @Override
    public boolean isAvailable() {
        return ContactsManager.isNotEmpty();
    }
}
