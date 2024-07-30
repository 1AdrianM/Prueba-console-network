package main.java.Commands;

import main.java.Interfaz.Command;
import main.java.Request.Http;

public class FollowCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length >= 3) {
            String userOrigen = args[1].substring(1); // Eliminar el carácter '@'
            String userDestino = args[2].substring(1); // Eliminar el carácter '@'
            Http.httpHandleFollow(userOrigen, userDestino);
        } else {
            System.out.println("El mensaje no tiene el formato correcto.");
        }
    }
}
