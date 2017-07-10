package com.mprtcz.tictactoeproject.message;

/**
 * Created by sergey on 10.07.17.
 *
 */
public interface Event {

    String getMessage();
    EventObserver.EventType getType();
    Object getData();
}
