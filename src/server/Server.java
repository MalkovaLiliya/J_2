package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    private final static int PORT = 8090;

    public Server() {
        Scanner inConsole = new Scanner(System.in);

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен");

            Socket socket = server.accept();
            System.out.println("Клиент подключен");

            new Thread(() -> {
                try (Scanner inSocket = new Scanner(socket.getInputStream())) {
                    while (true) {
                        String str = "";
                        try {
                            str = inSocket.nextLine();
                        } catch (NoSuchElementException e) {
                            System.out.println("Сокет закрыт");
                            System.exit(0);
                        }
                        if (str.equals("/end")) {
                            System.out.println("Клиент отключился");
                            System.exit(0);
                        }
                        System.out.println("Клиент: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    while (true) {
                        String str = inConsole.nextLine();
                        out.println(str);
                        if (str.equals("/end")) {
                            System.out.println("Сервер отключен");
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
