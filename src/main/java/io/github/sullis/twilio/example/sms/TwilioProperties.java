package io.github.sullis.twilio.example.sms;

import java.util.Properties;

public class TwilioProperties {
    private static Properties props = new Properties().load(TwilioProperties.class.getResourceAsStream("twilio.properties"));

    static public String getAccount() { return props.getProperty("twilio.account"); }

    static public String getPassword() { return props.getProperty("twilio.key"); }

}
