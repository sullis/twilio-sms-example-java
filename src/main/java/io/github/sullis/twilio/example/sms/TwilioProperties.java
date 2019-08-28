package io.github.sullis.twilio.example.sms;

import java.io.IOException;
import java.util.Properties;

public class TwilioProperties {
    private static Properties props;

    static {
         props = new Properties();
         try {
             props.load(TwilioProperties.class.getResourceAsStream("twilio.properties"));
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }

    static public String getAccount() { return props.getProperty("twilio.account"); }

    static public String getPassword() { return props.getProperty("twilio.key"); }

}
