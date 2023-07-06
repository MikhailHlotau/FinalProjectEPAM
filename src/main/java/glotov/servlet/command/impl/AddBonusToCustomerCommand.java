package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.service.CustomerService;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static glotov.servlet.util.PageName.ADMIN_CUSTOMERS_PAGE;
import static glotov.servlet.util.RequestAttributeName.BONUS_POINTS;
import static glotov.servlet.util.RequestAttributeName.CUSTOMER_ID;

public class AddBonusToCustomerCommand implements Command {
    private final CustomerService customerService;

    public AddBonusToCustomerCommand() {
        this.customerService = CustomerServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int customerId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
        int bonusPoints = Integer.parseInt(request.getParameter(BONUS_POINTS));
        customerService.addBonusPoints(customerId, bonusPoints);
        return ADMIN_CUSTOMERS_PAGE;
    }
}
