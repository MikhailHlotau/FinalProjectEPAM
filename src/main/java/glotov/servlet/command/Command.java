package glotov.servlet.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute (HttpServletRequest request);
}