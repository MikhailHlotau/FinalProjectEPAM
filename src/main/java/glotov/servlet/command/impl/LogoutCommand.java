package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static glotov.servlet.util.PageName.INDEX_PAGE;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return INDEX_PAGE;
    }
}

