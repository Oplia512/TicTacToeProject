package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.net.interfaces.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.interfaces.ConnectionProviderStateListener;
import com.mprtcz.tictactoeproject.net.interfaces.ConnectionProviderInitListener;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by sergey on 03.07.17.
 */
public class ConnectionProvider {

    static final int[] PORT = {3000, 3002};

    private TicTacToeClientProvider clientProvider;
    private TicTacToeServerProvider serverProvider;
    private ConnectionProviderStateListener stateListener;

    public ConnectionProvider(ConnectionProviderStateListener stateListener) {
        this.stateListener = stateListener;
    }

    private ConnectionProviderInitListener connectionProviderInitListener = new ConnectionProviderInitListener() {
        @Override
        public void clientConnected(int port) {
            System.out.println("Client has connected to a server on port: " + port);
        }

        @Override
        public void serverConnected(int port) {
            System.out.println("Server has connected to a client on port: " + port);
        }

        @Override
        public void serverCreated(int port) {
            System.out.println("Server created on port: " + port);
            System.out.println("Waiting a client ...");
            createClientSocket(port);
        }

        @Override
        public void clientConnectionFailed() {
            stateListener.clientConnectionFailed();
        }

        @Override
        public void serverCreationFailed() {
            stateListener.serverCreationFailed();
        }

        @Override
        public void connectionClosed() {
           stateListener.connectionClosed();
        }

    };

    public void initProvider(CommunicatorListener communicatorListener) throws IOException {
        serverProvider = new TicTacToeServerProvider(connectionProviderInitListener, communicatorListener);
        clientProvider = new TicTacToeClientProvider(connectionProviderInitListener, InetAddress.getLocalHost());
        serverProvider.tryToCreateServer();
    }

    private void createClientSocket(int port){
        if (port == PORT[0]){
            clientProvider.tryToCreateSocket(PORT[1], connectionProviderInitListener);
        } else {
            clientProvider.tryToCreateSocket(PORT[0], connectionProviderInitListener);
        }
    }
}
