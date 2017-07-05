package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.net.interfces.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.interfces.NetProviderInitListener;

import java.io.*;
import java.net.*;

/**
 * Created by sergey on 03.07.17.
 */
public class TicTacToeServer {

    private static final int BACKLOG = 1;
    private static final int SOCKET_WAITING_TIME_OUT = 30000;
    static final int[] PORT = {3000, 3001};


    private NetProviderInitListener netProviderListener;
    private CommunicatorListener communicatorListener;
    private ServerSocket server;

    TicTacToeServer(NetProviderInitListener netProviderListener, CommunicatorListener communicatorListener, int portId) throws IOException {
        server = new ServerSocket(PORT[portId], BACKLOG, InetAddress.getLocalHost());
        server.setSoTimeout(SOCKET_WAITING_TIME_OUT);
        this.netProviderListener = netProviderListener;
        this.communicatorListener = communicatorListener;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connectToClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void connectToClient() throws SecurityException, IOException{
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
