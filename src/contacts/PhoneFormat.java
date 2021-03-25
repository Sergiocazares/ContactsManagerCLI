package contacts;

public class PhoneFormat {
    private String format;
    private byte length;

    public PhoneFormat(String format, byte length) {
        setFormat(format);
        setLength(length);
    }

    public void setLength(byte length) {
        this.length = length;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public byte getLength() {
        return length;
    }

    public String getFormat() {
        return format;
    }

    public static String onlyNumbers(String phone) {

        StringBuilder newPhone = new StringBuilder();

        for(char ch: phone.toCharArray()){
            byte ascii = (byte) ch;
            if(ascii >= 48 && ascii <= 57) {
                newPhone.append(ch);
            }
        }

        return newPhone.toString();

    }
}
