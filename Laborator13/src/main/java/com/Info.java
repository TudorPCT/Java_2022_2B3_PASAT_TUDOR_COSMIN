package com;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
public class Info {
    public String getInfo(Locale locale){
        StringBuilder infos=new StringBuilder();
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(locale);
        DateFormatSymbols dateFormatSymbols=new DateFormatSymbols(locale);
        infos.append("Country: ");
        infos.append(locale.getDisplayCountry());
        infos.append("\n");
        infos.append("Language: ");
        infos.append(locale.getDisplayLanguage(locale));
        infos.append("\n");
        infos.append("Currency: ");
        infos.append(NumberFormat.getCurrencyInstance(locale).getCurrency().getDisplayName(locale));
        infos.append("\n");
        infos.append("Week days: ");
        infos.append(Arrays.toString(dateFormatSymbols.getWeekdays()));
        infos.append("\n");
        infos.append("Months: ");
        infos.append(Arrays.toString(dateFormatSymbols.getMonths()));
        infos.append("\n");
        infos.append("Today: ");
        infos.append(today.format(formatter));
        infos.append("\n");
    return String.valueOf(infos);
    }
}
