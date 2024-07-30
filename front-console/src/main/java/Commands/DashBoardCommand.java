package main.java.Commands;

import main.java.Interfaz.Command;
import main.java.Request.Http;

public class DashBoardCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length >= 2) {
            String user = args[1].substring(1);
            Http.httpHandleDashBoard(user);
        } else {
            System.out.println("Formato incorrecto");
        }
    }
}
