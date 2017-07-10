package com.mprtcz.tictactoeproject.net;

import com.mprtcz.tictactoeproject.message.EventObserver;
import com.mprtcz.tictactoeproject.message.event_impl.TicTacToeEvent;
import com.mprtcz.tictactoeproject.net.interfaces.ConnectionProviderInitListener;
import com.mprtcz.tictactoeproject.net.interfaces.SocketCreationListener;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static com.mprtcz.tictactoeproject.message.EventObserver.EventType.CLIENT_CONNECTION_FAILED;
import static com.mprtcz.tictactoeproject.message.EventObserver.EventType.CLIENT_SOCKET_CREATED;
import static com.mprtcz.tictactoeproject.message.EventObserver.EventType.CLIENT_SOCKET_DISCONNECTED;

/**
 * Created by sergey on 03.07.17.
 */
class TicTacToeClientProvider {

    private final int NUMBER_OF_TRIES = 5;
    private final int WAITING_TIME = 1000;

    private InetAddress address;
    private int tries = 0;
    private Thread thread;

    TicTacToeClientProvider(InetAddress address) throws IOException {
        this.address = address;
    }

    void tryToCreateSocket(int portId) {
        createSocket(portId, new SocketCreationListener() {
            @Override
            public void socketCreated(int port) {
                EventObserver.getInstance().notifiObservers(
                        new TicTacToeEvent.TicTakToeEventBuilder()
                        .setType(CLIENT_SOCKET_CREATED)
                        .setData(port)
                        .createEvent());
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
                    EventObserver.getInstance().notifiObservers(
                            new TicTacToeEvent.TicTakToeEventBuilder()
                            .setType(CLIENT_CONNECTION_FAILED)
                            .createEvent());
                }
            }
        });
        tries = 0;
    }

    private void createSocket(int port, SocketCreationListener creationListener) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Socket socket = new Socket(address, port);
                     DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                     BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
                    if (creationListener != null) {
                        creationListener.socketCreated(port);
                    }
                    String userInput;
                    while (!thread.isInterrupted() && (userInput = stdIn.readLine()) != null) {
                        dataOutputStream.writeUTF(userInput);
                        dataOutputStream.flush();
                    }
                    if (thread.isInterrupted()){
                        EventObserver.getInstance().notifiObservers(
                                new TicTacToeEvent.TicTakToeEventBuilder()
                                .setType(CLIENT_SOCKET_DISCONNECTED)
                                .setData(port)
                                .createEvent());

                    }
                } catch (IOException e) {
                    if (creationListener != null) {
                        creationListener.creationFailed();
                    }

                }
            }
        });
        thread.start();
    }

    void closeConnection(){
        thread.interrupt();
    }
}
