package menu;

import contacts.ContactsManager;
import input.Input;

public class SearchNumber extends MenuItem {

    public SearchNumber() {
        setDescription("Search by phone number");
    }

    @Override
    public boolean isAvailable() {
        return ContactsManager.numberOfContacts() > 0;
    }

    @Override
    public void action() {
        String searchStr = Input.getString("Enter a search parameter").toLowerCase();
        ContactsManager.searchByNumber(searchStr);
    }
}
