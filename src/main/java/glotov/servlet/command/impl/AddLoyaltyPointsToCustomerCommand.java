package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.service.CustomerService;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static glotov.servlet.util.PageName.ADMIN_CUSTOMERS_PAGE;
import static glotov.servlet.util.RequestAttributeName.CUSTOMER_ID;
import static glotov.servlet.util.RequestAttributeName.LOYALTY_POINTS;

public class AddLoyaltyPointsToCustomerCommand implements Command {
    private final CustomerService customerService;

    public AddLoyaltyPointsToCustomerCommand() {
        this.customerService = CustomerServiceImpl.getInstance();
    }
    @Override
    public String execute(HttpServletRequest request) {
        int customerId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
        int loyaltyPoints = Integer.parseInt(request.getParameter(LOYALTY_POINTS));
        try {
            customerService.addLoyaltyPoints(customerId, loyaltyPoints);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return ADMIN_CUSTOMERS_PAGE;
    }
}
