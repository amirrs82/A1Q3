package View.Menus;

import Controller.LoginMenuController;
import View.Enums.Commands.LoginMenuCommands;
import View.Enums.Messages.LoginMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;
        Matcher matcher;
        String snappfoodAdminUsername = scanner.nextLine().trim();
        String snappfoodAdminPassword = scanner.nextLine().trim();
        LoginMenuController.createSnappfoodAdmin(snappfoodAdminUsername, snappfoodAdminPassword);

        while (true) {
            command = scanner.nextLine().trim();
            if (command.equals("exit")) break;
            else if (LoginMenuCommands.getMatcher(command, LoginMenuCommands.SHOW_CURRENT_MENU) != null)
                System.out.println("login menu");
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.REGISTER)) != null)
                checkRegister(matcher);
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGIN)) != null)
                checkLogin(matcher, scanner);
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.CHANGE_PASSWORD)) != null)
                checkChangePassword(matcher);
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.REMOVE_ACCOUNT)) != null)
                checkRemoveAccount(matcher);
            else System.out.println("invalid command!");

        }
    }

    private void checkRegister(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        LoginMenuMessages message = LoginMenuController.checkRegister(username, password);
        switch (message) {
            case INVALID_USERNAME_FORMAT:
                System.out.println("register failed: invalid username format");
                break;
            case USERNAME_EXIST:
                System.out.println("register failed: username already exists");
                break;
            case INVALID_PASSWORD_FORMAT:
                System.out.println("register failed: invalid password format");
                break;
            case WEAK_PASSWORD:
                System.out.println("register failed: weak password");
                break;
            case SUCCESS:
                System.out.println("register successful");
                break;
        }
    }

    public void checkLogin(Matcher matcher, Scanner scanner) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        LoginMenuMessages message = LoginMenuController.checkLogin(username, password);
        switch (message) {
            case USERNAME_NOT_EXIST:
                System.out.println("login failed: username not found");
                break;
            case WRONG_PASSWORD:
                System.out.println("login failed: incorrect password");
                break;
            case SUCCESS:
                System.out.println("login successful");
                new MainMenu().run(scanner);
                break;
        }
    }

    public void checkChangePassword(Matcher matcher) {
        String username = matcher.group("username");
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        LoginMenuMessages message = LoginMenuController.checkChangePassword(username, oldPassword, newPassword);
        switch (message) {
            case USERNAME_NOT_EXIST:
                System.out.println("password change failed: username not found");
                break;
            case WRONG_PASSWORD:
                System.out.println("password change failed: incorrect password");
                break;
            case INVALID_PASSWORD_FORMAT:
                System.out.println("password change failed: invalid new password");
                break;
            case WEAK_PASSWORD:
                System.out.println("password change failed: weak new password");
                break;
            case SUCCESS:
                System.out.println("password change successful");
                break;
        }
    }

    private void checkRemoveAccount(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        LoginMenuMessages message = LoginMenuController.checkRemoveAccount(username, password);
        switch (message) {
            case USERNAME_NOT_EXIST:
                System.out.println("remove account failed: username not found");
                break;
            case WRONG_PASSWORD:
                System.out.println("remove account failed: incorrect password");
                break;
            case SUCCESS:
                System.out.println("remove account successful");
                break;
        }
    }
}
