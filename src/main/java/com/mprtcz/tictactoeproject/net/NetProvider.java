package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.net.interfces.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.interfces.NetProviderInitListener;

import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;

/**
 * Created by sergey on 03.07.17.
 */
public class NetProvider {

    private TicTacToeClient client;
    private TicTacToeServer server;
    private NetProviderInitListener netProviderInitListener;

    private int serverPortId;

    public void initProvider(NetProviderInitListener listener, CommunicatorListener communicatorListener) throws IOException {
        this.netProviderInitListener = listener;
        try {
            server = new TicTacToeServer(netProviderInitListener, communicatorListener, 0);
            serverPortId = 0;
        } catch (BindException e) {
            server = new TicTacToeServer(netProviderInitListener, communicatorListener, 1);
            serverPortId = 1;
        }
        if (listener != null) {
            listener.serverCreated();
        }
    }

    public void connectToServer() throws IOException {
        int clientPort = 0;
        if (serverPortId == 0){
            clientPort = 1;
        }
        for (int i = 0; i < 5; i++) {
            if (client != null) break;
            try {
                try {
                    client = new TicTacToeClient(InetAddress.getLocalHost(), clientPort);
                } catch (BindException e) {
                    client = new TicTacToeClient(InetAddress.getLocalHost(), clientPort);
                }

                if (netProviderInitListener != null) {
                    netProviderInitListener.clientCreated();
                }

            } catch (ConnectException e){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public TicTacToeClient getClient() {
        return client;
    }

    public TicTacToeServer getServer() {
        return server;
    }
}
