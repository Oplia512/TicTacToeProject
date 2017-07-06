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

    public static final int[] PORT = {3000, 3001};

    private TicTacToeClientProvider clientProvider;
    private TicTacToeServerProvider serverProvider;


    private NetProviderInitListener netProviderInitListener = new NetProviderInitListener() {
        @Override
        public void clientConnected() {

        }

        @Override
        public void serverCreated() {

        }

        @Override
        public void clientCreated() {

        }
    };

    public void initProvider(CommunicatorListener communicatorListener) throws IOException {
        serverProvider = new TicTacToeServerProvider(netProviderInitListener, communicatorListener);
        clientProvider = new TicTacToeClientProvider(InetAddress.getLocalHost());
        int serverPort = serverProvider.tryToCreateServer();
        if (serverPort == PORT[0]){
            clientProvider.createSocket(PORT[1]);
        } else {
            clientProvider.createSocket(PORT[0]);
        }
    }

}
