package main.java.Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
public class Http {
    public Http(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    private static String BASE_URL = "http://localhost:8080/api";
public static void httpHandleUserCreation(String name){
    try{
        URL url = new URL(BASE_URL+"/user/create");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        String jsonInputString = String.format(
                "{\"name\":\"%s\"}",
                    name);
                try(OutputStream os = conn.getOutputStream()){
                    byte[] inputBytes = jsonInputString.getBytes("utf-8");
                     os.write(inputBytes,0, inputBytes.length);
                }
                int responseCode = conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_CREATED) {
                    System.out.println("Usuario @"+name+" Creado con exito");
                }else{
                    System.out.println("Error al crear Usuario");
                }

    }catch (Exception e){
        e.printStackTrace();

    }
}
    public static void httpHandlePost(String username, String message) {
        String time = getCurrentTimestamp();
        try {
            URL url = new URL(BASE_URL + "/user/" + username + "/create/post");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Crear el JSON del cuerpo de la solicitud
            String jsonInputString = String.format(
                    "{\"id\":\"%s\",\"postOwner\":\"%s\",\"title\":\"%s\",\"text\":\"%s\",\"createdAt\":\"%s\"}",
                    generateUniqueId(), username, "Title of the post", message, time
            );

            try (OutputStream os = conn.getOutputStream()) {
                byte[] inputBytes = jsonInputString.getBytes("utf-8");
                os.write(inputBytes, 0, inputBytes.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println(username + " posted -> \"" + message + "\" @" + time);
            } else {
                System.out.println("Failed to post message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void httpHandleFollow(String userOrigen, String userDestino) {
        try {
            URL url = new URL(BASE_URL + "/user/follow/" + userDestino);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Crear el JSON del cuerpo de la solicitud
            String jsonInputString = String.format("{\"userOrigen\": \"%s\"}", userOrigen);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] inputBytes = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(inputBytes, 0, inputBytes.length);
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println(userOrigen + " empezó a seguir a " + userDestino);
            } else {
                System.out.println("Failed to follow user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void httpHandleDashBoard(String username) {
        try {
            URL url = new URL(BASE_URL + "/user/" + username + "/dashboard");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Procesar la respuesta

                String responseBody = response.toString();
                // Remove outer brackets
                responseBody = responseBody.substring(1, responseBody.length() - 1);
                // Split into individual JSON objects
                String[] messages = responseBody.split("\\},\\{");

                for (int i = 0; i < messages.length; i++) {
                    // Clean up the JSON strings
                    String message = messages[i].replace("{", "").replace("}", "").replace("\"", "").trim();
                    // Extract formattedResponse
                    String[] keyValue = message.split(":", 2);
                    if (keyValue.length == 2) {
                        String formattedResponse = keyValue[1].trim();
                        System.out.println(formattedResponse);
                    }
                }
            }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    private static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.now().format(formatter);
    }
}
