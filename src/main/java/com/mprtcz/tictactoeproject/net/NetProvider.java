package com.mprtcz.tictactoeproject.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by sergey on 03.07.17.
 */
public class NetProvider {

    private ServerClientDataTransferInterface dataTransferInterface;
    private boolean isServer;
    private NetProviderInitListener netProviderInitListener;
    private CommunicatorListener communicatorListener;


    public void initProvider(boolean isServer, NetProviderInitListener listener, CommunicatorListener communicatorListener) throws IOException {
        this.isServer = isServer;
        this.netProviderInitListener = listener;
        this.communicatorListener = communicatorListener;

        if (isServer){
            dataTransferInterface = initServer();
            listener.serverIsReady();
        } else {
            dataTransferInterface = initClient();
            listener.clientIsReady();
        }
    }

    private ServerClientDataTransferInterface initServer() throws IOException {
        return new TicTacToeServer(netProviderInitListener, communicatorListener);
    }

    public void connectServerToClient() throws IOException {
        ((TicTacToeServer) dataTransferInterface).connectToClient();
    }

    private ServerClientDataTransferInterface initClient() throws IOException{
        return new TicTacToeClient(InetAddress.getLocalHost(), communicatorListener);
    }

    public void sendMessage(String message) {
        dataTransferInterface.sendMessage(message);
    }

    public void closeConnection(){
        dataTransferInterface.closeConnection();
    }
}
