package glotov.servlet.command;

import glotov.servlet.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
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
    CANSEL_ORDER(new CancelOrderCommand());

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
