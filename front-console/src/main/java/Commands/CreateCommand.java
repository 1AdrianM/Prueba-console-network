package main.java.Commands;

import main.java.Interfaz.Command;
import main.java.Request.Http;

public class CreateCommand implements Command {
    @Override
    public void execute(String[] args) {
        if(args.length>=2) {
            String usuario = args[1].substring(1);
            Http.httpHandleUserCreation(usuario);
        }else{
            System.out.println("formato incorrecto");
        }
    }
}
