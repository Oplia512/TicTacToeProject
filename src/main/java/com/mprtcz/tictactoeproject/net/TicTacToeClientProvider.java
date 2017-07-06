package com.mprtcz.tictactoeproject.net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static com.mprtcz.tictactoeproject.net.NetProvider.PORT;

/**
 * Created by sergey on 03.07.17.
 */
class TicTacToeClientProvider {

    private final int NUMBER_OF_TRIES = 5;

    private InetAddress address;

    TicTacToeClientProvider(InetAddress address) throws IOException {
       this.address = address;
    }

    boolean tryToCreateSocket(){

    }

    private void createSocket(int portId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                    try (Socket socket = new Socket(address, PORT[portId]);
                         DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                         BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
                        System.out.println("server online");
                        String userInput;
                        while ((userInput = stdIn.readLine()) != null) {
                            dataOutputStream.writeUTF(userInput);
                            dataOutputStream.flush();
                        }
                    } catch (IOException e) {
                        e.getMessage();
                    }
            }
        }).start();
    }



//TODO create interface for pass messages from createSocket
}
