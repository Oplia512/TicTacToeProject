package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.net.interfaces.CommunicatorListener;
import com.mprtcz.tictactoeproject.net.interfaces.ConnectionProviderInitListener;
import com.mprtcz.tictactoeproject.net.interfaces.SocketCreationListener;

import java.io.*;
import java.net.*;

import static com.mprtcz.tictactoeproject.net.ConnectionProvider.PORT;

/**
 * Created by sergey on 03.07.17.
 */
class TicTacToeServerProvider {

    private static final int BACKLOG = 1;
    private static final int SOCKET_WAITING_TIME_OUT = 30000;


    private ConnectionProviderInitListener connectionProviderInitListener;
    private CommunicatorListener communicatorListener;
    private Thread thread;

    TicTacToeServerProvider(ConnectionProviderInitListener netProviderListener, CommunicatorListener communicatorListener) throws IOException {
        this.connectionProviderInitListener = netProviderListener;
        this.communicatorListener = communicatorListener;
    }

    void tryToCreateServer() {
        createServerSocket(PORT[0], new SocketCreationListener() {
            @Override
            public void socketCreated(int port) {
                connectionProviderInitListener.serverCreated(port);
            }

            @Override
            public void creationFailed() {
                System.out.println("Port " + PORT[0] + " is busy. Trying port " + PORT[1]+ " ...");
                createServerSocket(PORT[1], new SocketCreationListener() {
                    @Override
                    public void socketCreated(int port) {
                        connectionProviderInitListener.serverCreated(port);
                    }

                    @Override
                    public void creationFailed() {
                        System.out.println("Port " + PORT[0] + " and port " + PORT[1] + " are busy.");
                        connectionProviderInitListener.serverCreationFailed();
                    }
                });
            }
        });
    }

    void closeConnection(){
        thread.interrupt();
    }



    private void createServerSocket(final int port, final SocketCreationListener creationListener) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(port, BACKLOG, InetAddress.getLocalHost())) {
                    serverSocket.setSoTimeout(SOCKET_WAITING_TIME_OUT);
                    if (creationListener != null){
                        creationListener.socketCreated(port);
                    }
                    try (Socket client = serverSocket.accept();DataInputStream inputStream = new DataInputStream(client.getInputStream());
                         DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream())) {
                        if (connectionProviderInitListener != null) {
                            connectionProviderInitListener.clientConnected(port);
                        }
                        String message = "";
                        while (!thread.isInterrupted()) {
                            message = inputStream.readUTF();
                            if (communicatorListener != null) {
                                communicatorListener.onReceivedMessage(message);
                            }
                            if (message.equals("buy")){
                                dataOutputStream.writeUTF("buy");
                                dataOutputStream.flush();
                                thread.interrupt();
                                connectionProviderInitListener.serverConnectionClosed();
                            }
                        }
                    }

                } catch (BindException be) {
                    if (creationListener != null){
                        creationListener.creationFailed();
                    }
                } catch (EOFException eof){
                    thread.interrupt();
                    connectionProviderInitListener.serverConnectionClosed();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
