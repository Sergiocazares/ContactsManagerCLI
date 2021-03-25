package menu;

import contacts.ContactsManager;
import input.Input;

public class SearchName extends MenuItem {

    public SearchName() {
        setDescription("Search by name");
    }

    @Override
    public void action() {
        String searchStr = Input.getString("Enter a search parameter").toLowerCase();
        ContactsManager.searchByName(searchStr);
    }

    @Override
    public boolean isAvailable() {
        return ContactsManager.isNotEmpty();
    }

}
