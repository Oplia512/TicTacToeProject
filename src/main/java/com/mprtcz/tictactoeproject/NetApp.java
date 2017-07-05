package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.net.interfces.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.NetProvider;
import com.mprtcz.tictactoeproject.net.interfces.NetProviderInitListener;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sergey on 03.07.17.
 */
public class NetApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        NetProvider netProvider = new NetProvider();

        try {
            netProvider.initProvider(new NetProviderInitListener() {

                @Override
                public void clientConnected() {
                    System.out.println("clientConnected");
                }

                @Override
                public void serverCreated() {
                    System.out.println("serverCreated");
                }

                @Override
                public void clientCreated() {

                }

            }, new CommunicatorListener() {
                @Override
                public void onReceivedMessage(String message) {
                    System.out.println("message from client= [" + message + "]");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            netProvider.connectToServer();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
