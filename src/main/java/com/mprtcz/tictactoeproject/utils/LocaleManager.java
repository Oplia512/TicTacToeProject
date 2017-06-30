package com.mprtcz.tictactoeproject.utils;

import java.util.Locale;

/**
 * @author Michal_Partacz
 * @since 30.06.2017.
 */
public class LocaleManager {

    public static void validateInputAndSetLocale(String userInput) {
        AvailableLocale locale;
        System.out.println("userInput = " + userInput);
        switch (userInput.toLowerCase()) {
            case "pl":
                locale = AvailableLocale.PL; break;
            case "en":
                locale = AvailableLocale.EN; break;
            default:
                locale = AvailableLocale.DEFAULT;break;
        }
        setChosenLocale(locale);
    }

    private static void setChosenLocale(AvailableLocale locale) {
        Locale.setDefault(locale.getLocale());
    }

    public enum AvailableLocale {
        PL("Polish", "pl", new Locale("pl", "PL")),
        EN("English", "en", new Locale("en", "US")),
        DEFAULT("Default[English]", "anything", new Locale("en", "US"));

        String localeName;
        String nameShort;
        Locale locale;

        AvailableLocale(String localeName, String nameShort, Locale locale) {
            this.localeName = localeName;
            this.nameShort = nameShort;
            this.locale = locale;
        }

        public String getLocaleName() {
            return localeName;
        }

        public Locale getLocale() {
            return locale;
        }

        public String getNameShort() {
            return nameShort;
        }

        @Override
        public String toString() {
            return "AvailableLocale{" +
                    "localeName='" + localeName + '\'' +
                    ", nameShort='" + nameShort + '\'' +
                    ", locale=" + locale +
                    '}';
        }
    }
}
