package com.mprtcz.tictactoeproject.message;

import com.mprtcz.tictactoeproject.game.ExceptionsCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sergey on 10.07.17.
 */
public class EventObserver {

    public enum EventType{
        SERVER_SOCKET_CREATED,
        CLIENT_SOCKET_CREATED,
        SERVER_CONNECTED_TO_OPPONENT,
        SERVER_SOCKET_DISCONNECTED,
        CLIENT_SOCKET_DISCONNECTED,
        SERVER_SOCKET_CONNECTION_FAILED,
        CLIENT_CONNECTION_FAILED,
        OPPONENT_MESSAGE_RECEIVED
    }

    private static EventObserver instance;

    private final HashMap<String, EventProviderInterface> observers;

    private EventObserver() {
        observers = new HashMap<>();
    }

    public static void init(){
        instance = new EventObserver();
    }

    public void addObserver(EventProviderInterface observer){
        observers.put(observer.getClass().getName(), observer);
    }

    public void removeObserver(EventProviderInterface observer){
        observers.remove(observer.getClass().getName());
    }

    public void notifyObservers(Event event){
        Set<Map.Entry<String, EventProviderInterface>> set = observers.entrySet();
        for (Map.Entry<String, EventProviderInterface> entry : set  ) {
            entry.getValue().onReceiveEvent(event);
        }
    }

    public synchronized static EventObserver getInstance(){
        if (instance != null){
            return instance;
        } else {
            throw ExceptionsCreator.getInstance().createNotInitializedException(instance.getClass().getName());
        }
    }
}
