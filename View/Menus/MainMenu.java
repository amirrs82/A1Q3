package View.Menus;

import Controller.MainMenuController;
import View.Enums.Commands.MainMenuCommands;
import View.Enums.Messages.MainMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private boolean loggedOut = false;

    public void run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            if (loggedOut) break;
            command = scanner.nextLine().trim();
            if (command.equals("logout")) break;
            else if (MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_CURRENT_MENU) != null)
                System.out.println("main menu");
            else if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.ENTER_MENU)) != null)
                checkEnterMenu(matcher, scanner);
            else System.out.println("invalid command!");
        }
    }

    private void checkEnterMenu(Matcher matcher, Scanner scanner) {
        String menuName = matcher.group("menuName");
        MainMenuMessages message = MainMenuController.checkEnterMenu(menuName);
        switch (message) {
            case INVALID_MENU_NAME:
                System.out.println("enter menu failed: invalid menu name");
                break;
            case ACCESS_DENIED:
                System.out.println("enter menu failed: access denied");
                break;
            case SUCCESS:
                System.out.println("enter menu successful: You are in the " + menuName + "!");
                switch (menuName) {
                    case "customer menu":
                        loggedOut = new CustomerMenu().run(scanner);
                        break;
                    case "restaurant admin menu":
                        loggedOut = new RestaurantAdminMenu().run(scanner);
                        break;
                    case "Snappfood admin menu":
                        loggedOut = new SnappfoodAdminMenu().run(scanner);
                        break;
                }
                break;
        }
    }
}
