package input;

import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getString() {
        return getString("Enter an option");
    }

    public static String getString(String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }

}
