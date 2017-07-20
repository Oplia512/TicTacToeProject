package com.mprtcz.tictactoeproject.net;


import com.mprtcz.tictactoeproject.message.EventObserver;
import com.mprtcz.tictactoeproject.message.event_impl.TicTacToeEvent;
import com.mprtcz.tictactoeproject.net.interfaces.SocketCreationListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static com.mprtcz.tictactoeproject.message.EventObserver.EventType.*;
import static com.mprtcz.tictactoeproject.net.ConnectionProvider.PORT;

/**
 * Created by sergey on 03.07.17.
 */
class TicTacToeServerProvider {

    private static final int BACKLOG = 1;
    private static final int SOCKET_WAITING_TIME_OUT = 30000;

    private Thread thread;
    private final InetAddress inetAddress;

    TicTacToeServerProvider(final InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    void tryToCreateServer() {
        createServerSocket(PORT[0], new SocketCreationListener() {
            @Override
            public void socketCreated(int port) {
                EventObserver.getInstance().notifyObservers(
                        new TicTacToeEvent.TicTakToeEventBuilder()
                                .setType(SERVER_SOCKET_CREATED)
                                .setData(port)
                                .createEvent());
            }

            @Override
            public void creationFailed() {
                EventObserver.getInstance().notifyObservers(
                        new TicTacToeEvent.TicTakToeEventBuilder()
                                .setType(SERVER_SOCKET_CONNECTION_FAILED)
                                .setMessage("Port " + PORT[0] + " is busy. Trying port " + PORT[1]+ " ...")
                                .createEvent());

                createServerSocket(PORT[1], new SocketCreationListener() {
                    @Override
                    public void socketCreated(int port) {
                        EventObserver.getInstance().notifyObservers(
                                new TicTacToeEvent.TicTakToeEventBuilder()
                                        .setType(SERVER_SOCKET_CREATED)
                                        .setData(port)
                                        .createEvent());
                    }

                    @Override
                    public void creationFailed() {
                        EventObserver.getInstance().notifyObservers(
                                new TicTacToeEvent.TicTakToeEventBuilder()
                                        .setType(SERVER_SOCKET_CONNECTION_FAILED)
                                        .setMessage("Port " + PORT[0] + " and port " + PORT[1] + " are busy.")
                                        .createEvent());
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
                try (ServerSocket serverSocket = new ServerSocket(port, BACKLOG, inetAddress)) {
                    serverSocket.setSoTimeout(SOCKET_WAITING_TIME_OUT);
                    if (creationListener != null){
                        creationListener.socketCreated(port);
                    }
                    try (Socket client = serverSocket.accept();DataInputStream inputStream = new DataInputStream(client.getInputStream());
                         DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream())) {

                        EventObserver.getInstance().notifyObservers(
                                new TicTacToeEvent.TicTakToeEventBuilder()
                                .setType(SERVER_CONNECTED_TO_OPPONENT)
                                .setData(port)
                                .createEvent());

                        String message = "";
                        while (!thread.isInterrupted()) {
                            message = inputStream.readUTF();

                            EventObserver.getInstance().notifyObservers(
                                    new TicTacToeEvent.TicTakToeEventBuilder()
                                    .setType(OPPONENT_MESSAGE_RECEIVED)
                                    .setMessage(message)
                                    .createEvent());

                            if (message.equals("buy")){
                                dataOutputStream.writeUTF("buy");
                                dataOutputStream.flush();
                                thread.interrupt();
                                EventObserver.getInstance().notifyObservers(
                                        new TicTacToeEvent.TicTakToeEventBuilder()
                                        .setType(SERVER_SOCKET_DISCONNECTED)
                                        .setData(port)
                                        .createEvent());
                            }
                        }
                    }

                } catch (BindException be) {
                    if (creationListener != null){
                        creationListener.creationFailed();
                    }
                } catch (EOFException eof){
                    thread.interrupt();
                    EventObserver.getInstance().notifyObservers(
                            new TicTacToeEvent.TicTakToeEventBuilder()
                            .setType(SERVER_SOCKET_DISCONNECTED)
                            .createEvent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
