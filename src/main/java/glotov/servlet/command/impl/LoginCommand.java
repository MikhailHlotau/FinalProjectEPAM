package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.Customer;
import glotov.servlet.model.Role;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static glotov.servlet.util.Message.LOGIN_FAILED_MESSAGE;
import static glotov.servlet.util.PageName.*;
import static glotov.servlet.util.RequestAttributeName.FAILED;
import static glotov.servlet.util.RequestAttributeName.CUSTOMER;
import static glotov.servlet.util.RequestParameterName.LOGIN;
import static glotov.servlet.util.RequestParameterName.PASSWORD;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String page = null;
        try {
            Customer authenticatedCustomer = customerService.authenticate(login, password);
            if (authenticatedCustomer != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", authenticatedCustomer);
                Role role = authenticatedCustomer.getRole();
                if (role.equals(Role.ADMIN)) {
                    request.setAttribute(CUSTOMER, login);
                    page = ADMIN_MENU_PAGE;
                    session.setAttribute("current_page", ADMIN_MENU_PAGE);
                } else if (role.equals(Role.USER)) {
                    request.setAttribute(CUSTOMER, login);
                    page = CUSTOMER_MENU_PAGE;
                }
            } else {
                request.setAttribute(FAILED, LOGIN_FAILED_MESSAGE);
                page = INDEX_PAGE;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
        return page;
    }
}