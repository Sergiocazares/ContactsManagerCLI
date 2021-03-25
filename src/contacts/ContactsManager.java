package contacts;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContactsManager {
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String GREEN = "\u001B[32m";  // GREEN
    public static final String RESET = "\033[0m";  // Text Reset

    public static final String FILE_NAME = "contactsInfo.txt";

    private static final HashMap<String, Contact> contacts = new HashMap<>();

    public static final ArrayList<PhoneFormat> PHONE_LENGTHS = new ArrayList<PhoneFormat>(Arrays.asList(
            new PhoneFormat("###-####", (byte) 7),
            new PhoneFormat("###-###-####", (byte) 10)));

    public static void addContact(Contact contact){
        contacts.put(contact.getName().toLowerCase(), contact);
    }

    public static void addData(List<String> data) {
        for (short i = 0; i < data.size(); i += 2) {
            String name = data.get(i);
            String phoneNumber = data.get(i + 1);
            addContact(new Contact(name, phoneNumber));
        }
    }

    public static void printContacts(){
        System.out.println(CYAN + "Name      " + GREEN + "|" + CYAN + " Phone Number " + GREEN + "|");
        System.out.println("--------------------------" + RESET);
        for(String name: contacts.keySet()) {
            System.out.printf("%-10s" + GREEN + "| " + RESET, contacts.get(name).getName());
            System.out.printf("%-13s" + GREEN + "|\n" + RESET, contacts.get(name).getPhoneNumber());
        }
        System.out.println();
    }

    public static void deleteContact(String name){
        contacts.remove(name.toLowerCase());
    }

    public static boolean hasContact(String name) {
        return contacts.containsKey(name.toLowerCase());
    }

    public static boolean hasPhoneNumber(String phoneNumber) {
        for(Contact contact : contacts.values()) {
            if(contact.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty() {
        return contacts.size() != 0;
    }

    public static void saveData() {
        System.out.println("\nSaving...\n");

        try {
            FileWriter myWriter = new FileWriter("data/" + FILE_NAME);
            for (Contact contact: values()) {
                myWriter.write(contact.getName() + "\n");
                myWriter.write(contact.getPhoneNumber() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("\nSave file missing\n");
        }
    }

    public static void loadFromFile(String fileName) throws IOException {
        Path path = Paths.get("data", fileName);

        if (Files.exists(path)) {
            List<String> fileContents = Files.readAllLines(path);
            addData(fileContents);
        } else {
            System.out.println("\nFile missing: creating new file\n");
            Files.createFile(path);
        }
    }

    public static int numberOfContacts() {
        return contacts.size();
    }

    public static Set<String> keys() {
        return contacts.keySet();
    }

    public static Collection<Contact> values() {
        return contacts.values();
    }

    public static Contact getContact(String name) {
        return contacts.get(name);
    }

    public static void searchByName(String searchStr) {
        search(searchStr, "name");
    }

    public static void searchByNumber(String searchStr) {
        search(searchStr, "phoneNumber");
    }

    private static void search(String searchStr, String field) {
        ArrayList<Contact> results = new ArrayList<>();

        for (Contact contact: values()) {

            String str = field.equalsIgnoreCase("name") ? contact.getName() : contact.getPhoneNumber();

            if (str.toLowerCase().startsWith(searchStr)) {
                results.add(contact);
            }
        }

        System.out.printf("\n%d result%s for '%s'\n\n", results.size(), results.size() == 1 ? "" : "s", searchStr);
        System.out.println(CYAN + "Name      " + GREEN + "|" + CYAN + " Phone Number " + GREEN + "|");
        System.out.println("--------------------------" + RESET);

        for (Contact contact: results) {
            System.out.printf("%-10s" + GREEN + "| " + RESET, contact.getName());
            System.out.printf("%-13s" + GREEN + "|\n" + RESET,contact.getPhoneNumber());
        }

        System.out.println();
    }

    public static boolean isValidPhoneNumber(String number){
        for(PhoneFormat format: PHONE_LENGTHS){
            if(number.length() == format.getLength()){
                return true;
            }
        }
        return false;
    }

    public static String formatPhoneNumber(String number) {

        String format = getFormat(number); //###-###-####
        StringBuilder formattedNumber = new StringBuilder(); //""

        System.out.println("formatPhoneNumber");
        System.out.println("number = " + number);

        if (format.equalsIgnoreCase("default")) {
            return number;
        }

        byte strIndex = 0;

        for (char ch: format.toCharArray()) {
            if (ch == '#') {
                formattedNumber.append(number.toCharArray()[strIndex]);
                strIndex++;
                if (number.length() == strIndex){
                    break;
                }
            } else {
                formattedNumber.append(ch);
            }
        }

        return formattedNumber.toString();

    }

    public static String getFormat(String phoneNumber) {
        for(PhoneFormat format: PHONE_LENGTHS){
            if(phoneNumber.length() == format.getLength()){
                return format.getFormat();
            }
        }
        return "default";
    }

}