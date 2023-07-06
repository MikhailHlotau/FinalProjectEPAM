package glotov.servlet.command.impl;


import glotov.servlet.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import static glotov.servlet.util.PageName.INDEX_PAGE;

public class DefaultCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return INDEX_PAGE;
    }
}
