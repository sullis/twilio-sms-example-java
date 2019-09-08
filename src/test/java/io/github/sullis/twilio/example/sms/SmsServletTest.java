package io.github.sullis.twilio.example.sms;


import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmsServletTest {

    @Test
    public void happyPath() throws Exception {
        final String taco = "🌮";
        final String requestBody = taco + taco;
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addParameter("From", "555-555-5555");
        req.addParameter("To", "777-777-7777");
        req.addParameter("Body", requestBody);
        MockHttpServletResponse response = new MockHttpServletResponse();
        SmsServlet servlet = new SmsServlet();
        servlet.doPost(req, response);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<Response><Message from=\"777-777-7777\" to=\"555-555-5555\">"
                + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f32e.png</Media>"
                + "<Media>https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f32e.png</Media>"
                + "</Message></Response>",
                response.getContentAsString());
    }
}
