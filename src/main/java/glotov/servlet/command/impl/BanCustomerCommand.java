package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.service.CustomerService;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static glotov.servlet.util.PageName.ADMIN_CUSTOMERS_PAGE;
import static glotov.servlet.util.RequestAttributeName.CUSTOMER_ID;

public class BanCustomerCommand implements Command {
    private final CustomerService customerService;

    public BanCustomerCommand() {
        this.customerService = CustomerServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int customerId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
        customerService.banCustomer(customerId);
        return ADMIN_CUSTOMERS_PAGE;
    }
}