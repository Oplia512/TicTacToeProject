package com.mprtcz.tictactoeproject.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * Created by sergey on 03.07.17.
 */
public class TicTacToeServer extends ServerSocket implements ServerClientDataTransferInterface {

    private static final int BACKLOG = 1;
    private static final int SOCKET_WAITING_TIME_OUT = 15000;
    static final int PORT = 3000;
    private boolean inProcess;

    private NetProviderInitListener netProviderListener;
    private CommunicatorListener communicatorListener;


    TicTacToeServer(NetProviderInitListener netProviderListener, CommunicatorListener communicatorListener) throws IOException {
        super(PORT, BACKLOG);
        setSoTimeout(SOCKET_WAITING_TIME_OUT);
        this.netProviderListener = netProviderListener;
        this.communicatorListener = communicatorListener;
    }

    void connectToClient() throws SecurityException, IOException{
        Socket client = accept();
        if (netProviderListener != null){
            netProviderListener.clientConnected();
        }
        inProcess = true;
        try (DataInputStream inputStream = new DataInputStream(client.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(client.getOutputStream())){
            startToCommunicate(inputStream);
        } catch (IOException e){
            e.getMessage();
            inProcess = false;
        } finally {
            close();
        }
    }

    private String getMessage(DataInputStream inputStream) throws IOException {
       return inputStream.readUTF();
    }

    private void startToCommunicate(DataInputStream inputStream) throws IOException {
        while (inProcess){
           String message = getMessage(inputStream);
           if (communicatorListener != null){
               communicatorListener.onReceivedMessage(message);
           }
        }
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void closeConnection() {
        inProcess = false;
        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
