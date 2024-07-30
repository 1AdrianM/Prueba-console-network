package main.java;

import main.java.Request.ApiRequest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado =  new Scanner(System.in);
        boolean banderita = true;
        System.out.println("----------Console NetWork-----------");
        System.out.println("------------BIENVENIDO--------------");
        System.out.println("* Para hacer publicaciones- post @usuario texto\n");
        System.out.println("* Para seguir a usuarios - follow @tuUsuario @Usuario\n");
        System.out.println("* Para ver tu muro - dashboard @tuUsuario\n");
        System.out.println("* Para crear un usuario -  create @Usuario\n");
        System.out.println("* Cerrar para Salir");
        while(banderita){

            String response = teclado.nextLine();
           if(response.startsWith("Cerrar")){
               banderita= false;

           }
            ApiRequest.handleRequest(response);

        }
    }
}


