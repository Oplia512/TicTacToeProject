package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.net.interfaces.ConnectionProviderInitListener;
import com.mprtcz.tictactoeproject.net.interfaces.SocketCreationListener;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by sergey on 03.07.17.
 */
class TicTacToeClientProvider {

    private final int NUMBER_OF_TRIES = 5;
    private final int WAITING_TIME = 1000;

    private InetAddress address;
    private int tries = 0;
    private ConnectionProviderInitListener connectionProviderInitListener;

    TicTacToeClientProvider(ConnectionProviderInitListener connectionProviderInitListener, InetAddress address) throws IOException {
        this.address = address;
        this.connectionProviderInitListener = connectionProviderInitListener;
    }

    void tryToCreateSocket(int portId, final ConnectionProviderInitListener connectionProviderInitListener) {
        createSocket(portId, new SocketCreationListener() {
            @Override
            public void socketCreated(int port) {
                connectionProviderInitListener.serverConnected(port);
            }

            @Override
            public void creationFailed() {
                if (tries < NUMBER_OF_TRIES) {
                    try {
                        tries++;
                        Thread.sleep(WAITING_TIME);
                        createSocket(portId, this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    connectionProviderInitListener.clientConnectionFailed();
                }
            }
        });
        tries = 0;
    }

    private void createSocket(int port, SocketCreationListener creationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (Socket socket = new Socket(address, port);
                     DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                     BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
                    if (creationListener != null) {
                        creationListener.socketCreated(port);
                    }
                    String userInput;
                    while ((userInput = stdIn.readLine()) != null) {
                        dataOutputStream.writeUTF(userInput);
                        dataOutputStream.flush();
                        if (userInput.equals("buy")) {
                            dataOutputStream.close();
                            stdIn.close();
                            socket.close();
                            connectionProviderInitListener.connectionClosed();
                            return;
                        }
                    }
                } catch (IOException e) {
                    if (creationListener != null) {
                        creationListener.creationFailed();
                    }

                }
            }
        }).start();
    }
}
