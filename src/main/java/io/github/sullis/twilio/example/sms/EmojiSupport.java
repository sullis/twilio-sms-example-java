package io.github.sullis.twilio.example.sms;

import com.google.common.collect.ImmutableList;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.text.translate.UnicodeEscaper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EmojiSupport {
    public static final Emoji DEFAULT_EMOJI = EmojiManager.getForAlias("wave");

    public static String urlFor(Emoji emoji) {
        return urlFor(emoji.getUnicode());
    }

    public static String urlFor(String unicode) {
        String hex = toHexString(unicode);
        return "https://sullis.github.io/noto-emoji-tools/assets/400/emoji_" + hex + ".png";
    }

    public static List<String> extractEmojis(String input) {
        if (input == null) {
            return new ArrayList<String>();
        }
        List<String> emojis = EmojiParser.extractEmojis(input);
        if (emojis.isEmpty()) {
            StringTokenizer tokenizer = new StringTokenizer(input);
            while (tokenizer.hasMoreTokens()) {
                Emoji e = EmojiManager.getForAlias(tokenizer.nextToken().trim().toLowerCase());
                if (e != null) {
                    emojis.add(e.getUnicode());
                }
            }
        }
        return emojis;
    }

    private static String toHexString(String input) {
        UnicodeEscaper escaper = new UnicodeEscaper();
        StringTokenizer tokenizer = new StringTokenizer(escaper.translate(input), "\\u");
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append('u');
        while (tokenizer.hasMoreTokens()) {
            if (count > 0) {
                sb.append('_');
            }
            sb.append(tokenizer.nextToken().toLowerCase());
            count++;
        }
        return sb.toString();
    }
}
