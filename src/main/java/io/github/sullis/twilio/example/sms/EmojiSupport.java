package io.github.sullis.twilio.example.sms;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import org.apache.commons.text.translate.UnicodeEscaper;

public class EmojiSupport {
    public static final Emoji DEFAULT_EMOJI = EmojiManager.getForAlias("wave");

    public static String urlFor(Emoji emoji) {
        return urlFor(emoji.getUnicode());
    }

    public static String urlFor(String unicode) {
        String hex = toHexString(unicode);
        return "https://sullis.github.io/noto-emoji-tools/assets/400/emoji_" + hex + ".png";
    }

    private static String toHexString(String input) {
        UnicodeEscaper escaper = new UnicodeEscaper();
        return escaper.translate(input).replace("\\u", "u").toLowerCase();
    }
}
