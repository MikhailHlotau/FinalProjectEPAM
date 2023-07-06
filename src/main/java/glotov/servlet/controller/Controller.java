package glotov.servlet.controller;

import glotov.servlet.command.Command;
import glotov.servlet.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import static glotov.servlet.util.RequestParameterName.COMMAND;

@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandStr = request.getParameter(COMMAND);
        logger.log(Level.INFO, "1. Выполнен метод doGet() String commandStr = request.getParameter(COMMAND)");
        Command command = CommandType.define(commandStr);
        logger.log(Level.INFO, "2. Выполнен метод doGet() Command command = CommandType.define(commandStr)");
        String page = command.execute(request);
        logger.log(Level.INFO, "3. Выполнен метод doGet() String page = command.execute(request)");
        request.getRequestDispatcher(page).forward(request, response);
        logger.log(Level.INFO, "4. Выполнен метод doGet() request.getRequestDispatcher(page).forward(request, response)");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    public void destroy() {
    }
}
