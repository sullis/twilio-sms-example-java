package io.github.sullis.twilio.example.sms;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

public class EmojiSupport {
    public static final Emoji DEFAULT_EMOJI = EmojiManager.getForAlias("wave");

    public static String urlFor(Emoji emoji) {
        String hex = toHexString(emoji);
        return "https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u" + hex + ".png";
    }

    private static String toHexString(Emoji emoji) {
        final int codePointCount = emoji.getUnicode().codePointCount(0, emoji.getUnicode().length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codePointCount; i++) {
            if (i > 0) {
                sb.append("_");
            }
            sb.append(Integer.toHexString(emoji.getUnicode().codePointAt(i)));
        }
        return sb.toString();
    }
}
