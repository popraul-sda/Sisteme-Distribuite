package org.example;


import java.rmi.Naming;


public class HelloWorldClient {

    static String message = "blank";
    // The HelloWorld object "obj" is the identifier that is
    // used to refer to the remote object that implements
    // the HelloWorld interface.

    static HelloWorld obj = null;
    public static void main(String args[]) {
        String n1 = "123";
        String n2 = "456";
        System.out.println ("\n The first no is " + n1);
        System.out.println (" The second no is "+ n2);
//        HelloWorld rmiinter= (HelloWorld)Naming.lookup("rmi://localhost/AdunaNumere");
////        double d1, d2;
////        d1=Double.valueOf(n1).doubleValue(); d2=Double.valueOf(n2).doubleValue();
////        System.out.println ("\n The sum of two values " + rmiinter.add (d1, d2));

        try {

            obj = (HelloWorld) Naming.lookup("//"
                   + "cti.ubm.ro"
                   // + "localhost"
                    + "/HelloWorld");
            message = obj.helloWorld();
            System.out.println("Message from the RMI-server was: \""
                    + message + "\"");

        } catch (Exception e) {
            System.out.println("HelloWorldClient exception: "
                    + e.getMessage());
            e.printStackTrace();
        }
    }
}
