package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorld extends Remote {
    
    String helloWorld() throws RemoteException;
}
