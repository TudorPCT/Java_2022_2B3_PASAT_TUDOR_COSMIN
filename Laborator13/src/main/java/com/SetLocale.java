package com;

import java.util.Locale;

public class SetLocale {
    public String setLocale(Locale locale){
        Locale.setDefault(locale);
        return locale.toString();
    }
}
