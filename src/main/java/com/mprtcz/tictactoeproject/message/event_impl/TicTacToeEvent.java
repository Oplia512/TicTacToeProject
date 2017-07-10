package com.mprtcz.tictactoeproject.message.event_impl;

import com.mprtcz.tictactoeproject.message.Event;
import com.mprtcz.tictactoeproject.message.EventObserver;


/**
 * Created by sergey on 10.07.17.
 */
public class TicTacToeEvent implements Event{

    private EventObserver.EventType eventType;
    private String eventMessage;
    private Object data; //TODO implement some kind of data

    private TicTacToeEvent(EventObserver.EventType eventType, String eventMessage, Object data) {
        this.eventType = eventType;
        this.eventMessage = eventMessage;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return eventMessage;
    }

    @Override
    public EventObserver.EventType getType() {
        return eventType;
    }

    @Override
    public Object getData() {
        return data;
    }

    public static class TicTakToeEventBuilder {

        private EventObserver.EventType nestedType;
        private String nestedMessage;
        private Object nestedData;


        public TicTakToeEventBuilder setType(EventObserver.EventType type){
            this.nestedType = type;
            return this;
        }

        public TicTakToeEventBuilder setMessage(String message){
            this.nestedMessage = message;
            return this;
        }

        public TicTakToeEventBuilder setData(Object data){
            this.nestedData = data;
            return this;
        }

        public Event createEvent(){
            return new TicTacToeEvent(nestedType, nestedMessage, nestedData);
        }
    }
}
