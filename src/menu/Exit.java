package menu;

import contacts.ContactsManager;

public class Exit extends MenuItem {

    public Exit() {
        setDescription("Exit");
    }

    @Override
    public void action() {
        ContactsManager.saveData();
        System.out.println("Thank you and have a nice day!");
        System.exit(0);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

}