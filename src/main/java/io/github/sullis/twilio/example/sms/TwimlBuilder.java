package io.github.sullis.twilio.example.sms;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Media;
import com.twilio.twiml.messaging.Message;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

public class TwimlBuilder {
    public MessagingResponse build(String from, String to, String requestBody) {
        Media image = buildMedia(requestBody);
        Message msg = new Message.Builder().from(to).to(from).media(image).build();
        MessagingResponse messagingResponse = new MessagingResponse.Builder().message(msg).build();
    }

    protected Media buildMedia(String input) {
        Emoji emoji = null;
        if (input != null) {
            input = input.trim();
        }
        if (input.length() > 0) {
            emoji = EmojiManager.getByUnicode(input);
            if (emoji == null) {
                emoji = EmojiManager.getForAlias(input.toLowerCase());
            }
        }
        if (emoji == null) {
            emoji = EmojiSupport.DEFAULT_EMOJI;
        }
        return new Media.Builder(EmojiSupport.urlFor(emoji)).build();
    }
}
