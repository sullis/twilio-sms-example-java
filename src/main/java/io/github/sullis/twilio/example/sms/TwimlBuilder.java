package io.github.sullis.twilio.example.sms;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Media;
import com.twilio.twiml.messaging.Message;
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
      List<String> emojis = EmojiSupport.extractEmojis(input);
      if (emojis.isEmpty()) {
          emojis.add(EmojiSupport.DEFAULT_EMOJI.getUnicode());
      }
      emojis.forEach(s -> list.add(buildMedia(s)));
      return list;
    }

    protected Media buildMedia(String unicode) {
        return new Media.Builder(EmojiSupport.urlFor(unicode)).build();
    }
}
