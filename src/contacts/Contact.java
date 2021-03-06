package contacts;

public class Contact {

    private String name;
    private String phoneNumber;

    public Contact(String aName, String aPhoneNumber) {
        this.name = aName;
        this.phoneNumber = aPhoneNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactInfo(){
        String contactInfo = getName() + " " + getPhoneNumber();
        return contactInfo;
    }
}
