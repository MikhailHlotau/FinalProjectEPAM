package glotov.servlet.command;

import glotov.servlet.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION_CUSTOMER (new RegistrationCustomerCommand()),
    DEFAULT(new DefaultCommand()),
    FIND_ALL_MENU_ITEMS_COMMAND (new FindAllMenuItemsCommand()),
    PLACE_ORDER(new PlaceOrderCommand()),
    CALCULATE_PRICE(new CalculatePriceCommand()),
    PAY_ORDER(new PayOrderCommand()),
    ADD_MENU_ITEM(new AddMenuItemCommand()),
    REMOVE_MENU_ITEM(new RemoveMenuItemCommand()),
    BAN_CUSTOMER(new BanCustomerCommand()),
    ADD_BONUS_TO_CUSTOMER(new AddBonusToCustomerCommand()),
    ADD_LOYALTY_POINTS_TO_CUSTOMER(new AddLoyaltyPointsToCustomerCommand()),
    RATE_ORDER(new RateOrderCommand()),
    LEAVE_REVIEW(new LeaveReviewCommand()),
    FIND_ALL_CUSTOMERS(new FindAllCustomersCommand()),
    CANSEL_ORDER(new CancelOrderCommand());

    private static final Logger logger = LogManager.getLogger();
    final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        Command command;
        if (commandStr != null) {
            try {
                CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
                command = commandType.command;
            } catch (IllegalArgumentException e) {
                command = DEFAULT.command;
            }
        } else {
            command = DEFAULT.command;
        }
        return command;
    }
}
