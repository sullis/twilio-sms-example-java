package io.github.sullis.twilio.example.sms;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Media;
import com.twilio.twiml.messaging.Message;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import java.util.ArrayList;
import java.util.List;

public class TwimlBuilder {
    public MessagingResponse build(String from, String to, String requestBody) {
        Message.Builder mbuilder = new Message.Builder().from(from).to(to);
        List<Media> mlist = buildMediaList(requestBody);
        mlist.forEach( m -> mbuilder.media(m));
        Message msg = mbuilder.build();
        return new MessagingResponse.Builder().message(msg).build();
    }

    protected List<Media> buildMediaList(String input) {
      List<Media> list = new ArrayList<Media>();
      list.add(buildMedia(input));
      return list;
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
