package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        String clientSentence;
        String capitalizedSentence;

        ServerSocket welcomeSocket = new ServerSocket(80);
        System.out.println("1. Listening on "+ welcomeSocket.getLocalSocketAddress());

        while(true){
            try {
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("2. Listening to" + connectionSocket.getInetAddress());
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                System.out.println("Local: " + connectionSocket.getLocalAddress() + ":" + connectionSocket.getLocalPort() + ", " + connectionSocket.getLocalSocketAddress());
                System.out.println("Remote: " + connectionSocket.getInetAddress() + ":" + connectionSocket.getPort() + ", " + connectionSocket.getRemoteSocketAddress());
                System.out.println("Received: " + clientSentence);
                capitalizedSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
            }
            catch (Exception ex)
            {
                System.out.println("ERROR: "+ ex.toString());
            }
        }
    }
}
