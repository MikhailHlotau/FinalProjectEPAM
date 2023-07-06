package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.Customer;
import glotov.servlet.service.CustomerService;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllCustomersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.log(Level.INFO, "Начало метода execute(HttpServletRequest request) FindAllCustomersCommand");
        CustomerService customerService = CustomerServiceImpl.getInstance();
        List<Customer> customers = null;
        try {
            customers = customerService.findAllCustomers();
            logger.log(Level.INFO, "Конец метода execute(HttpServletRequest request) FindAllCustomersCommand");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("customers", customers);

        return "pages/customers.jsp"; // Путь к JSP странице для отображения списка пользователей
    }
}
