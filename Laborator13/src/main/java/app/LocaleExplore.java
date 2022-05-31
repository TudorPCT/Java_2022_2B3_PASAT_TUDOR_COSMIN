package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static String displayMessages(Locale locale, String message) {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        DisplayLocales displayLocales=new DisplayLocales();
        SetLocale setLocale=new SetLocale();
        Info info=new Info();
        if(message.equals(""))
            return messages.getString("prompt");
        else if(message.equals("locales"))
        {
            return messages.getString("locales")+ displayLocales.displayLocales();
        }
        else if(message.startsWith("local.set "))
        {   message=message.substring(10);

            String locale1=setLocale.setLocale(Locale.forLanguageTag(message));
            String output = messages.getString("locale.set");
            Object[] arguments={locale1};
            return new MessageFormat(output).format(arguments);
        }
        else if(message.startsWith("info"))
        {
            message=message.substring(5);
            Locale location=Locale.forLanguageTag(message);
            if(message.isEmpty())
            {
                message=locale.getCountry();
                 location= locale;
            }
            String output = messages.getString("info");
            Object[] arguments={message};
            return new MessageFormat(output).format(arguments)+ "\n"+info.getInfo(location);
        }
        else
            return messages.getString("invalid");
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command;
        while(true)
        {
            System.out.println(displayMessages(Locale.getDefault(),""));
            command= scanner.nextLine();
            if(command.equals("exit"))
                break;
            else
                System.out.println(displayMessages(Locale.getDefault(),command));
        }

    }
}
