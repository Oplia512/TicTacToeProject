package com.mprtcz.tictactoeproject.net;

/**
 * Created by sergey on 03.07.17.
 */
public interface NetProviderInitListener {

    void clientIsReady();
    void clientConnected();
    void serverIsReady();
}
