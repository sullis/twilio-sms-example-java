
package io.github.sullis.twilio.example.sms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmojiSupportTest {
  @Test
  public void testUrlFor() {
    assertEquals("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f44b.png",
            EmojiSupport.urlFor(EmojiSupport.DEFAULT_EMOJI));
  }

}