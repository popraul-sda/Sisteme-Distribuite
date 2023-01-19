package org.example;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class TCPEchoServer
{
    public static void main(String args[])
    {
        int []vector = new int[10];
        String vectorNume = "0123456789"; //ne ajuta sa retinem pozitia rezervata de fiecare
        char numeInit = 0;
        Map<Character, String> dictionary = new HashMap<Character, String>();

        int port = 8910;
        ServerSocket serverSocket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;

        try
        {
// open a server socket
            serverSocket = new ServerSocket(port);
            System.out.println("Server created on port "+port);
            System.out.println("Awaiting client connection...");
// await for a client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from "+clientSocket.getInetAddress());
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        }
        catch(IOException e)
        {
            System.out.println("Problems initializing server: "+e);
            exit(1);
        }
// communicate with the client
        try
        {
            dataOutputStream.writeUTF("Welcome to the TCP Echo Server!");
            String input;
            while(true)
            {

// read data in from client

                input = dataInputStream.readUTF();
                System.out.println("You typed: " + input);
                String[] splited = input.split("\\s+");

// write data back to client
                if(input.contains("AUT"))
                {
                    dataOutputStream.writeUTF("Hello, " + splited[1] + "!");

                    numeInit = splited[1].charAt(0);
                    dictionary.put(numeInit, splited[1]);
                }
                else if(input.contains("REZ")) {
                   //daca pozitia este libera
                   if(vector[Integer.parseInt(splited[1])] == 0) {

                       dataOutputStream.writeUTF("Pozitia " + splited[1] + " a fost rezervata");

                       //schimbam valoarea pozitiei respective cu 1 in vector (1 = ocupat)
                       vector[Integer.parseInt(splited[1])] = 1;

                       vectorNume = vectorNume.substring(0,Integer.parseInt(splited[1]))
                               + numeInit+ vectorNume.substring(Integer.parseInt(splited[1]) + 1);

                       //daca pozitia e ocupata
                   }else{
                       dataOutputStream.writeUTF("Pozitia " + splited[1] + " este ocupata");
                   }

                }
               else if(input.contains("LST")){
                   String verifPoz = "Pozitiile";
                   int counter = 0;

                   for(int i = 0 ; i < 10; i++){
                       if (vector[i] == 1)
                       {
                           if (counter == 0)
                           {
                               verifPoz += " " + i;
                           }
                           else
                           {
                               verifPoz += ", " + i;
                           }
                           counter++;
                       }

                }
                   if(counter == 0){
                       dataOutputStream.writeUTF("Toate pozitiile sunt libere");
                   } else {
                       dataOutputStream.writeUTF(verifPoz + ", sunt ocupate");
                   }

            }
              else if(input.contains("BYE")){
                    System.out.println(vectorNume);
                   dataOutputStream.writeUTF("Server inchis");
                   exit(1);
               }
                    else {
                    dataOutputStream.writeUTF("Comanda invalida");
                }

            }
        }
        catch(IOException e)
        {
            System.out.println("Client disconnected from server");
        }
        try
        {
            serverSocket.close();
        }
        catch(Exception e) { }
    }
}
