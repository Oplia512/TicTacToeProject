package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.message.Event;
import com.mprtcz.tictactoeproject.message.EventProviderInterface;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by sergey on 03.07.17.
 */
public class ConnectionProvider implements EventProviderInterface {

    static final int[] PORT = {3000, 3002};

    private TicTacToeClientProvider clientProvider;
    private TicTacToeServerProvider serverProvider;


    public void initProvider(InetAddress currentAddress, InetAddress opponentAddress) throws IOException {
        serverProvider = new TicTacToeServerProvider(currentAddress);
        clientProvider = new TicTacToeClientProvider(opponentAddress);
        serverProvider.tryToCreateServer();
    }

    private void createClientSocket(int port){
        if (port == PORT[0]){
            clientProvider.tryToCreateSocket(PORT[1]);
        } else {
            clientProvider.tryToCreateSocket(PORT[0]);
        }
    }


    @Override
    public void onReceiveEvent(Event event) {
        switch (event.getType()){
            case SERVER_SOCKET_CREATED:
                System.out.println("Server created on port: " + event.getData());
                System.out.println("Waiting for a client ...");
                createClientSocket((Integer) event.getData());
                break;
            case SERVER_CONNECTED_TO_OPPONENT:
                System.out.println("Server has connected to a client on port: " + event.getData());
                break;
            case CLIENT_SOCKET_CREATED:
                System.out.println("Client has connected to a server on port: " + event.getData());
                break;
            case SERVER_SOCKET_DISCONNECTED:
                clientProvider.closeConnection();
                System.out.println("Server connection closed!");
                break;
            case CLIENT_SOCKET_DISCONNECTED:
                System.out.println("Client connection closed!");
                break;
            case CLIENT_CONNECTION_FAILED:
                System.out.println("Client connection failed!");
                break;
            case SERVER_SOCKET_CONNECTION_FAILED:
                System.out.println(event.getMessage());
                break;
            case OPPONENT_MESSAGE_RECEIVED:
                System.out.println("Message: " + event.getMessage());
                break;
        }
    }
}
