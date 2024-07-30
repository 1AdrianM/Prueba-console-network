package main.java.Request;

import main.java.Commands.DashBoardCommand;
import main.java.Commands.CreateCommand;
import main.java.Commands.FollowCommand;
import main.java.Commands.PostCommand;
import main.java.Interfaz.Command;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("post", new PostCommand());
        commands.put("follow", new FollowCommand());
        commands.put("dashboard", new DashBoardCommand());
        commands.put("create", new CreateCommand());
    }

    public static void handleRequest(String message) {
        message = message.trim().replaceAll(" +", " ");
        String[] data = message.split(" ", 3);
        if(data[0].startsWith("Cerrar")){
            System.out.println("Cerrando Sesion...");
        }
        else if (data.length > 0) {
            Command command = commands.get(data[0]);
            if (command != null) {
                command.execute(data);
            } else {
                System.out.println("Comando desconocido: " + data[0]);
            }
        } else {
            System.out.println("No se ha proporcionado ningún comando.");
        }

    }

}
