package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private final static int PORT = 8090;
    private final static String IP_ADDRESS = "localhost";

    public Client() {
        Scanner inConsole = new Scanner(System.in);

        try {
            Socket socket = new Socket(IP_ADDRESS, PORT);
            System.out.println("Клиент подключился");

            new Thread(() -> {
                try (Scanner inSocket = new Scanner(socket.getInputStream())) {
                    while (true) {
                        String str;
                        try {
                            str = inSocket.nextLine();
                        } catch (NoSuchElementException e) {
                            System.out.println("Сокет закрыт");
                            inConsole.close();
                            break;
                        }
                        if (str.equals("/end")) {
                            System.out.println("Сервер отключен");
                            System.exit(0);
                        }
                        System.out.println("Сервер: " + str);
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
                            System.out.println("Клиент отключился");
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
        new Client();
    }
}
