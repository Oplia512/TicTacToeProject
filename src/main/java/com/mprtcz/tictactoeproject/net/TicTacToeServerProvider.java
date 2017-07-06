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

    private void createServerSocket(final int port, final SocketCreationListener creationListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(port, BACKLOG, InetAddress.getLocalHost())) {
                    serverSocket.setSoTimeout(SOCKET_WAITING_TIME_OUT);
                    if (creationListener != null){
                        creationListener.socketCreated(port);
                    }
                    connectClient(serverSocket, port);

                } catch (BindException be) {
                    if (creationListener != null){
                        creationListener.creationFailed();
                    }
                } catch (EOFException eof){
                    connectionProviderInitListener.connectionClosed();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void connectClient(ServerSocket server, int port) throws SecurityException, IOException {
        Socket client = server.accept();
        if (connectionProviderInitListener != null) {
            connectionProviderInitListener.clientConnected(port);
        }
        startToListen(client, server);
    }

    private void startToListen(final Socket client, final ServerSocket serverSocket) throws IOException {
        try (DataInputStream inputStream = new DataInputStream(client.getInputStream())) {
            String message = "";
            while (true) {
                message = inputStream.readUTF();
                if (communicatorListener != null) {
                    communicatorListener.onReceivedMessage(message);
                }
                if (message.equals("bye")){
                    inputStream.close();
                    client.close();
                    serverSocket.close();
                    connectionProviderInitListener.connectionClosed();
                    return;
                }
            }
        }
    }
}
