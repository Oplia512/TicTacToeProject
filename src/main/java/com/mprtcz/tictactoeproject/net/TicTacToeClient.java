package com.mprtcz.tictactoeproject.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by sergey on 03.07.17.
 */
public class TicTacToeClient extends Socket implements ServerClientDataTransferInterface{

    static final int PORT = 3000;

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private NetProviderInitListener netProviderInitListener;

    public TicTacToeClient(InetAddress address, NetProviderInitListener netProviderInitListener) throws IOException {
        super(address, PORT);
        dataInputStream = new DataInputStream(getInputStream());
        dataOutputStream = new DataOutputStream(getOutputStream());
        this.netProviderInitListener = netProviderInitListener;
        if (netProviderInitListener != null){
            netProviderInitListener.clientIsReady();
        }
    }

    @Override
    public void sendMessage(String message) {

        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
