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
public class TicTacToeClient extends Socket implements ServerClientDataTransferInterface {

    static final int PORT = 3000;

    private CommunicatorListener communicatorListener;

    public TicTacToeClient(InetAddress address, CommunicatorListener communicatorListener) throws IOException {
        super(address, PORT);
        this.communicatorListener = communicatorListener;
    }

    @Override
    public void sendMessage(String message) {
        try (DataOutputStream outputStream = new DataOutputStream(getOutputStream())) {
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
