package org.example;

import java.io.*;
import java.util.Scanner;

// create class BullyAlgoExample1 to understand how bully algorithms works
class BullyAlgoExample1 {

    // declare variables and arrays for process and their status
    static int numberOfProcess;
    static int priorities[] = new int[100];
    static int status[] = new int[100];
    static int coord;

    // main() method start
    public static void main(String args[]) throws IOException // handle IOException
    {
        // get input from the user for the number of processes
        //System.out.println("Enter total number of processes:");

        // create scanner class object to get input from user
        Scanner sc = new Scanner(System.in);

        //numberOfProcess = sc.nextInt();
        numberOfProcess = 5;
        int i;

        // use for loop to set priority and status of each process
        for(i = 1; i <= numberOfProcess; i++)
        {
            //System.out.println("Status for process " + i + ":");

            //status[i] = sc.nextInt();
            status[i] = 1;

            if(i == 2)
                status[i] = 0;

            //System.out.println("Priority of process " + i + ":");

            //priorities[i] = sc.nextInt();
            priorities[i] = i;
        }

        System.out.println("Enter proces which will initiate election");
        int ele = sc.nextInt();
        sc.close();

        if(status[ele] == 0){
            System.out.println("Sorry, process " + ele + " is down.");
            System.exit(1);
        }

        // call electProcess() method
        electProcess(ele);
        System.out.println("After electing process the final coordinator is " + coord);
    }
    // create electProcess() method
    static void electProcess(int ele)
    {
        coord = ele;
        //System.out.println("I'm " + ele + " with priority " + priorities[ele]);

        for(int i = 1; i <= numberOfProcess; i++)
        {
            if(priorities[ele] < priorities[i])
            {
                System.out.println("Election message is sent from "+ ele + " to " + i );
                if(status[i] == 1)
                    electProcess(i);
            }
        }
    }
}