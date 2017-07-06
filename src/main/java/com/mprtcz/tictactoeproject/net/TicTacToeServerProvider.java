package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.net.interfces.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.interfces.NetProviderInitListener;

import java.io.*;
import java.net.*;

import static com.mprtcz.tictactoeproject.net.NetProvider.PORT;

/**
 * Created by sergey on 03.07.17.
 */
 class TicTacToeServerProvider {

    private static final int BACKLOG = 1;
    private static final int SOCKET_WAITING_TIME_OUT = 30000;


    private NetProviderInitListener netProviderListener;
    private CommunicatorListener communicatorListener;

    TicTacToeServerProvider(NetProviderInitListener netProviderListener, CommunicatorListener communicatorListener) throws IOException {
        this.netProviderListener = netProviderListener;
        this.communicatorListener = communicatorListener;
    }

    public int tryToCreateServer(){
        try{
            try {
                createServerSocket(PORT[0]);
                return PORT[0];
            } catch (BindException e){
                createServerSocket(PORT[1]);
                return PORT[1];
            }
        } catch (IOException io){
            io.getMessage();
            return -1;
        }
    }

    private void createServerSocket(final int portId) throws BindException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try(ServerSocket serverSocket = new ServerSocket(PORT[portId], BACKLOG, InetAddress.getLocalHost())) {
                    serverSocket.setSoTimeout(SOCKET_WAITING_TIME_OUT);
                    if (netProviderListener != null){
                        netProviderListener.serverCreated();
                    }
                    connectToClient(serverSocket);

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void connectToClient(ServerSocket server) throws SecurityException, IOException{
        Socket client = server.accept();
        if (netProviderListener != null){
            netProviderListener.clientConnected();
        }
        startToListen(client);
    }

    private void startToListen(final Socket client) throws IOException {
            try (DataInputStream inputStream = new DataInputStream(client.getInputStream())){
                String message = "";
                while (!message.equals("buy")){
                    message = inputStream.readUTF();
                    if (communicatorListener != null){
                        communicatorListener.onReceivedMessage(message);
                    }
                }
            }
    }
}
