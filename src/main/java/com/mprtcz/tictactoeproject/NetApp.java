package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.message.EventObserver;
import com.mprtcz.tictactoeproject.net.ConnectionProvider;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by sergey on 03.07.17.
 */
public class NetApp {

    public static void main(String[] args) {

        ConnectionProvider connectionProvider = new ConnectionProvider();
        EventObserver.init();
        EventObserver.getInstance().addObserver(connectionProvider);

        try {
            connectionProvider.initProvider(InetAddress.getLocalHost(), InetAddress.getLocalHost());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
