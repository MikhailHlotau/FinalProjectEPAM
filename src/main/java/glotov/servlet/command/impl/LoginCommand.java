package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static glotov.servlet.util.Message.LOGIN_FAILED_MESSAGE;
import static glotov.servlet.util.PageName.INDEX_PAGE;
import static glotov.servlet.util.PageName.MAIN_PAGE;
import static glotov.servlet.util.RequestAttributeName.FAILED;
import static glotov.servlet.util.RequestAttributeName.USER;
import static glotov.servlet.util.RequestParameterName.LOGIN;
import static glotov.servlet.util.RequestParameterName.PASSWORD;

public class LoginCommand implements Command {

    CustomerServiceImpl userService = CustomerServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String page;
        if (userService.authenticate(login, password)) {
            request.setAttribute(USER, login);
            page = MAIN_PAGE;
        } else {
            request.setAttribute(FAILED, LOGIN_FAILED_MESSAGE);
            page = INDEX_PAGE;
        }
        return page;
    }
}