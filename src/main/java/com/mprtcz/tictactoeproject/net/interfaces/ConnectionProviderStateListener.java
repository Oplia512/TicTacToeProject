package com.mprtcz.tictactoeproject.net.interfaces;

/**
 * Created by Sergey on 07.07.2017.
 */
public interface ConnectionProviderStateListener {
    void clientConnectionFailed();
    void serverCreationFailed();
    void connectionClosed();
}
