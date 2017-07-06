package com.mprtcz.tictactoeproject.net.interfaces;

/**
 * Created by Sergey on 06.07.2017.
 */

public interface SocketCreationListener {

    void socketCreated(int port);
    void creationFailed();
}
