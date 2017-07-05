package com.mprtcz.tictactoeproject.net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by sergey on 03.07.17.
 */
public class TicTacToeClient {

    static final int[] PORT = {3000, 3001};

    private Socket socket;

    public TicTacToeClient(InetAddress address, int portId) throws IOException {
        socket = new Socket(address, PORT[portId]);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("server online");
                try (DataOutputStream dataOutputStream = new DataOutputStream(getOutputStream()); BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
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

    private OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    private InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }


}
