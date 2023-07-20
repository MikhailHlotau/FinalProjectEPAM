package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.exception.DaoException;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.Customer;
import glotov.servlet.service.CustomerService;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static glotov.servlet.util.PageName.LIST_ALL_CUSTOMERS;
import static glotov.servlet.util.RequestAttributeName.CUSTOMERS;

public class FindAllCustomersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        CustomerService customerService = CustomerServiceImpl.getInstance();
        List<Customer> customers = null;
        try {
            customers = customerService.findAllCustomers();

        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
        request.setAttribute(CUSTOMERS, customers);

        return LIST_ALL_CUSTOMERS;
    }
}
