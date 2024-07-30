package main.java.Commands;
import main.java.Request.Http;
import main.java.Interfaz.Command;
public class PostCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length >= 3) {
            String user = args[1].substring(1);
            String message = args[2];
            Http.httpHandlePost(user, message);
        } else {
            System.out.println("Formato incorrecto");
        }
    }
}
