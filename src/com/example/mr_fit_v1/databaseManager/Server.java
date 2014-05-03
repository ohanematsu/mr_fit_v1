package com.example.mr_fit_v1.databaseManager;

import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    
    public void start(int portNumber) {        
        // Create server socket
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber);
            System.exit(1);
        }
        System.out.println("Server starts to listen...");
        
        // Create connection with client
        try {
            while (true) {
                ServerSideSocketClient socket = new ServerSideSocketClient(serverSocket.accept());
                System.out.println("Receive a new connection...");
                socket.start();
            }
        } catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }
    }
    
}
