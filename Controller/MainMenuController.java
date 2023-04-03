package Controller;

import Model.*;
import View.Enums.Messages.MainMenuMessages;

public class MainMenuController {
    public static MainMenuMessages checkEnterMenu(String menuName) {
        if (Controller.isValidMenuName(menuName))
            if (Controller.hasAccess(menuName, Snappfood.getCurrentUser()))
                return MainMenuMessages.SUCCESS;
            else return MainMenuMessages.ACCESS_DENIED;
        else return MainMenuMessages.INVALID_MENU_NAME;
    }
}
