package io.github.sullis.twilio.example.sms;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

public class EmojiSupport {
    public static final Emoji DEFAULT_EMOJI = EmojiManager.getForAlias("wave");

    public static String urlFor(Emoji emoji) {
        String hex = toHexString(emoji.getUnicode());
        return "https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u" + hex + ".png";
    }

    public static String urlFor(String unicode) {
        String hex = toHexString(unicode);
        return "https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u" + hex + ".png";
    }

    private static String toHexString(String s) {
        final int codePointCount = s.codePointCount(0, s.length());
        System.out.println("codePointCount: " + codePointCount);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codePointCount; i++) {
            final int codePoint = s.codePointAt(i);
            System.out.println("codePoint: " + codePoint);
            if (i > 0) {
                sb.append("_1");
            }
            sb.append(Integer.toHexString(codePoint));
        }
        return sb.toString();
    }
}
