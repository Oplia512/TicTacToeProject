package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.net.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.NetProvider;
import com.mprtcz.tictactoeproject.net.NetProviderInitListener;

import java.io.IOException;

/**
 * Created by sergey on 03.07.17.
 */
public class ServerApp {

    public static void main(String[] args) {


        NetProvider netProvider = new NetProvider();
        try {
            netProvider.initProvider(true, new NetProviderInitListener() {
                @Override
                public void clientIsReady() {

                }

                @Override
                public void clientConnected() {
                    netProvider.sendMessage("Hello fromServer!");
                }

                @Override
                public void serverIsReady() {
                    netProvider.connectServerToClient();
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
    }
}
