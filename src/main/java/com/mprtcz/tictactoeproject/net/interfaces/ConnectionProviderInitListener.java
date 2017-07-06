package com.mprtcz.tictactoeproject.net.interfaces;

/**
 * Created by sergey on 03.07.17.
 */
public interface ConnectionProviderInitListener extends ConnectionProviderStateListener {

    void clientConnected(int port);
    void serverConnected(int port);
    void serverCreated(int port);
}
