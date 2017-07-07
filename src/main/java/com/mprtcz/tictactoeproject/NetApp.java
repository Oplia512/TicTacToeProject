package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.net.interfaces.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.ConnectionProvider;
import com.mprtcz.tictactoeproject.net.interfaces.ConnectionProviderStateListener;

import java.io.IOException;

/**
 * Created by sergey on 03.07.17.
 */
public class NetApp {

    public static void main(String[] args) {

        ConnectionProvider connectionProvider = new ConnectionProvider(new ConnectionProviderStateListener() {
            @Override
            public void clientConnectionFailed() {
                System.out.println("Client connection failed!");
            }

            @Override
            public void serverCreationFailed() {
                System.out.println("Server creation failed!");
            }

            @Override
            public void serverConnectionClosed() {
                System.out.println("Server connection closed!");
            }

            @Override
            public void clientConnectionClosed() {
                System.out.println("Client connection closed!");
            }


        });

        try {
            connectionProvider.initProvider(new CommunicatorListener() {
                @Override
                public void onReceivedMessage(String message) {
                    System.out.println("message from client= [" + message + "]");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
