package com.mprtcz.tictactoeproject.utils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Michal_Partacz
 * @since 30.06.2017.
 */
public class MessagesWrapper {
    ResourceBundle resourceBundle;

    public MessagesWrapper() {
        this.resourceBundle = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());
    }

    public String getString(String key) {
        try {
            return new String(resourceBundle.getString(key).getBytes("ISO-8859-1"), "Windows-1250");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
