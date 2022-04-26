package client;

import common.Settings;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws UnknownHostException {

        int port = Integer.parseInt(ResourceBundle.getBundle("settings").getString("port"));
        String host = ResourceBundle.getBundle("settings").getString("host");

        InetAddress address = InetAddress.getByName(host);
        try (Client client = new Client(Settings.HOST, Settings.PORT,(message) -> {
            System.out.println(message.getText());
            })) {

            System.out.println("Ready to send");
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String command = scanner.nextLine();

                    client.send(command);

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(0);
        }
    }

}