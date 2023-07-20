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
        logger.log(Level.INFO,"Выполнен doGet(): request.getParameter(COMMAND)");
        Command command = CommandType.define(commandStr);
        logger.log(Level.INFO,"Выполнен doGet(): CommandType.define(commandStr)");
        String page = command.execute(request);
        logger.log(Level.INFO,"Выполнен doGet(): command.execute(request)");
        request.getRequestDispatcher(page).forward(request, response);
        logger.log(Level.INFO,"Выполнен doGet(): request.getRequestDispatcher(page).forward(request, response)");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandStr = request.getParameter(COMMAND);
        logger.log(Level.INFO,"Выполнен doPost(): request.getParameter(COMMAND)");
        Command command = CommandType.define(commandStr);
        logger.log(Level.INFO,"Выполнен doPost(): CommandType.define(commandStr)");
        String page = command.execute(request);
        logger.log(Level.INFO,"Выполнен doPost(): command.execute(request)");
        request.getRequestDispatcher(page).forward(request, response);
        logger.log(Level.INFO,"Выполнен doPost(): request.getRequestDispatcher(page).forward(request, response)");
    }

    public void destroy() {
    }
}