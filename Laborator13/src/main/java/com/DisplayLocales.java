package com;

import java.util.Locale;

public class DisplayLocales {
    public String displayLocales() {
        StringBuilder avLocales=new StringBuilder();
        Locale[] available =
                Locale.getAvailableLocales();
        for (Locale locale : available) {
            avLocales.append(locale.getDisplayCountry());
            avLocales.append("\t");
            avLocales.append(locale.getDisplayLanguage(locale));
            avLocales.append("\n");
        }
    return avLocales.toString();
    }
}
