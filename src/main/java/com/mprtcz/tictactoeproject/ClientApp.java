package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.net.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.NetProvider;
import com.mprtcz.tictactoeproject.net.NetProviderInitListener;

import java.io.IOException;

/**
 * Created by sergey on 03.07.17.
 */
public class ClientApp {

    public static void main(String[] args) {
        NetProvider netProvider = new NetProvider();
        try {
            netProvider.initProvider(false, new NetProviderInitListener() {
                @Override
                public void clientIsReady() {
                    netProvider.sendMessage("Hello!");
                }

                @Override
                public void clientConnected() {
                    //Do nothing
                }

                @Override
                public void serverIsReady() {
                    //Do nothing
                }
            }, new CommunicatorListener() {
                @Override
                public void onReceivedMessage(String message) {
                    System.out.println("message from server = [" + message + "]");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
