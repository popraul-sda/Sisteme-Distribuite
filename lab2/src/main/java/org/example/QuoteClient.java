package org.example;

import java.io.*;
import java.net.*;

public class QuoteClient {
    public static void main(String[] args) {
        String hostname;
        int port;
        if (args.length < 2) {
            System.out.println("Syntax: QuoteClient <hostname> <port>");
            //return;
            hostname = "djxmmx.net";
            port = 17;
        } else {
            hostname = args[0];
            port = Integer.parseInt(args[1]);
        }

        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();

            while (true) {
                DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                long startTime = System.currentTimeMillis();
                socket.send(request);
                byte[] buffer = new byte[1024];

                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                long endTime = System.currentTimeMillis();
                String quote = new String(buffer, 0, response.getLength() - 1);

                System.out.println(quote);
                System.out.println();
                System.out.println("timp: " + (endTime-startTime)+ " ms");
                System.out.println();

                Thread.sleep(5000);
            }
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}