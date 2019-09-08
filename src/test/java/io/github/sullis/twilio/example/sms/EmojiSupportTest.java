
package io.github.sullis.twilio.example.sms;

import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.Fitzpatrick;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmojiSupportTest {

  @Test
  public void testUrlFor_basicEmojis() {
    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f44b.png",
            EmojiSupport.urlFor(EmojiSupport.DEFAULT_EMOJI));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f436.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("dog")));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f431.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("cat")));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f308.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("rainbow")));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u26be.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("baseball")));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f44d.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("thumbsup")));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f44b.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("wave")));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("surfer").getUnicode()));
  }

  @Test
  public void testUrlFor_fitzpatrickModifiers() {
    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fb.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("surfer").getUnicode(Fitzpatrick.TYPE_1_2)));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fc.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("surfer").getUnicode(Fitzpatrick.TYPE_3)));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fd.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("surfer").getUnicode(Fitzpatrick.TYPE_4)));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fe.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("surfer").getUnicode(Fitzpatrick.TYPE_5)));

    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3ff.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("surfer").getUnicode(Fitzpatrick.TYPE_6)));
  }

  // TODO @Test
  public void xtestUrlFor_flags() {
    // Ireland flag
    // https://emojipedia.org/flag-for-ireland/
    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_uXXX.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("ie")));

    // Mexico flag
    // https://emojipedia.org/flag-for-mexico/
    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_uXXX.png",
            EmojiSupport.urlFor(EmojiManager.getForAlias("mx")));
  }

}