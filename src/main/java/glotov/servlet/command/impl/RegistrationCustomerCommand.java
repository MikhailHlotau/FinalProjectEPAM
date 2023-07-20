package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.Customer;
import glotov.servlet.model.Role;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static glotov.servlet.util.Message.LOGIN_ALREADY_EXISTS_MESSAGE;
import static glotov.servlet.util.PageName.CUSTOMER_MENU_PAGE;
import static glotov.servlet.util.PageName.REGISTRATION_PAGE;
import static glotov.servlet.util.RequestAttributeName.*;
import static glotov.servlet.util.RequestParameterName.*;

public class RegistrationCustomerCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);
        String page;

        try {
            if (customerService.existsByLogin(login)) {
                request.setAttribute(MESSAGE, LOGIN_ALREADY_EXISTS_MESSAGE);
                page = REGISTRATION_PAGE;
            } else {
                Customer customer = new Customer.CustomerBuilder()
                        .withLogin(login)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withPassword(password)
                        .withPhone(phone)
                        .withEmail(email)
                        .withRole(Role.USER)
                        .build();

                customerService.registerCustomer(customer);
                request.setAttribute(CUSTOMER, login);
                page = CUSTOMER_MENU_PAGE;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
        return page;
    }
}
