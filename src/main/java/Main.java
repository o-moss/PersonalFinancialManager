import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        Statistic statistic = new Statistic();

        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            while (true) {
                try (Socket socket = serverSocket.accept(); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream());) {
                    String json = in.readLine();
                    Gson gson = new Gson();
                    Purchase purchase = gson.fromJson(json, Purchase.class);
                    statistic.addPurchase(purchase);
                    String jsonAnswer = gson.toJson(statistic.findMax());
                    out.println(jsonAnswer);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}

