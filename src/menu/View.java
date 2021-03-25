package menu;

import contacts.ContactsManager;

public class View extends MenuItem {

    public View() {
        setDescription("View contacts");
    }

    @Override
    public boolean isAvailable() {
        return ContactsManager.isNotEmpty();
    }

    @Override
    public void action() {
        ContactsManager.printContacts();
    }
}
