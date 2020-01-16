package io.github.sullis.twilio.example.sms;


import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.Fitzpatrick;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmsServletTest {
    static private final String fakeAuthToken = "correctHorse";
    static private final String taco = "🌮";
    static private final Emoji surfer = EmojiManager.getForAlias("surfer");
    static private final String surfers = surfer.getUnicode()
            + " " + surfer.getUnicode(Fitzpatrick.TYPE_1_2)
            + " " + surfer.getUnicode(Fitzpatrick.TYPE_3)
            + " " + surfer.getUnicode(Fitzpatrick.TYPE_4)
            + " " + surfer.getUnicode(Fitzpatrick.TYPE_5)
            + " " + surfer.getUnicode(Fitzpatrick.TYPE_6);

    @Test
    public void one_taco() throws Exception {
        MockHttpServletResponse response = processRequest(taco);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<Response><Message from=\"777-777-7777\" to=\"555-555-5555\">"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f32e.png</Media>"
                        + "</Message></Response>",
                response.getContentAsString());
    }
    @Test
    public void two_tacos() throws Exception {
        MockHttpServletResponse response = processRequest(taco + taco);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<Response><Message from=\"777-777-7777\" to=\"555-555-5555\">"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f32e.png</Media>"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f32e.png</Media>"
                        + "</Message></Response>",
                response.getContentAsString());
    }

    @Test
    public void surfers() throws Exception {
        MockHttpServletResponse response = processRequest(surfers);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<Response><Message from=\"777-777-7777\" to=\"555-555-5555\">"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4.png</Media>"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fb.png</Media>"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fc.png</Media>"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fd.png</Media>"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3fe.png</Media>"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f3c4_1f3ff.png</Media>"
                        + "</Message></Response>",
                response.getContentAsString());
    }

    @Test
    public void supportEmojiAlias() throws Exception {
        final String alias = "apple";
        MockHttpServletResponse response1 = processRequest(alias);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<Response><Message from=\"777-777-7777\" to=\"555-555-5555\">"
                        + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f34e.png</Media>"
                        + "</Message></Response>",
                response1.getContentAsString());

        MockHttpServletResponse response2 = processRequest(EmojiManager.getForAlias(alias).getUnicode());
        assertEquals(response1.getContentAsString(), response2.getContentAsString());
    }

    private MockHttpServletResponse processRequest(String requestBody) throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addParameter("From", "555-555-5555");
        req.addParameter("To", "777-777-7777");
        req.addParameter("Body", requestBody);
        MockHttpServletResponse response = new MockHttpServletResponse();
        SmsServlet servlet = new SmsServlet(fakeAuthToken);
        servlet.doPost(req, response);
        return response;
    }
}
