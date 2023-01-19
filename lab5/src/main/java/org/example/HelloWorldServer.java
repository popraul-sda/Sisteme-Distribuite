package org.example;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorldServer extends UnicastRemoteObject implements HelloWorld {
    public HelloWorldServer() throws RemoteException {
        super(0);
    }
    public String helloWorld() {
        System.out.println("Invocation to helloWorld was succesful!");
        return "Hello World from RMI server!";
    }
    public static void main(String args[]) throws Exception {
        System.setProperty("java.rmi.server.hostname", "193.231.17.125");
        System.out.println("RMI server started");
        try { //special exception handler for registry creation
             LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
// Create an object of the HelloWorldServer class.
    HelloWorldServer obj = new HelloWorldServer();
// Bind this object instance to the name "HelloServer".
   Naming.rebind("HelloWorld", obj);
   System.out.println("HelloWorld bound in registry");
    } }