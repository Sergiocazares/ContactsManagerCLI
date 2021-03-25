package menu;

import input.Input;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu {

    private static final ArrayList<MenuItem> menu = new ArrayList<>();
    private static ArrayList<Integer> availableActions = new ArrayList<>();

    public static void createMenu(MenuItem... items) {
        menu.addAll(Arrays.asList(items));
    }

    public static void printOptions() {
        availableActions = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).isAvailable()) {
                availableActions.add(i);
                System.out.printf("%d: %s", availableActions.size(), menu.get(i).getDescription());
                System.out.println();
            }
        }
    }

    public static int getInput() {

        int index = 0;
        boolean repeat;

        do {
            printOptions();
            try {
                index = getOption();
                repeat = index < 1 || index > availableActions.size();
                if (repeat) {
                    System.out.println("\n!!!Invalid Input!!!");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("\n!!!Invalid Input!!!\n");
                repeat = true;
            }

        } while (repeat);

        return index;
    }

    public static int getOption() throws NumberFormatException {
        return Integer.parseInt(Input.getString());
    }

    public static void runOption(int option) {
        menu.get(availableActions.get(option - 1)).action();
    }
}
