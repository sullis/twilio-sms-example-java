
package io.github.sullis.twilio.example.sms;

import com.twilio.Twilio;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Media;
import com.twilio.twiml.messaging.Message;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "sms", value = "/")
public class SmsServlet extends HttpServlet {

  @Override
  public void init() {
    Twilio.init("hello", "world");
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/xml");
    PrintWriter out = resp.getWriter();
    Media image = new Media.Builder("https://sullis.github.io/noto-emoji-tools/assets/400/emoji_u1f5fb.png").build();
    Message msg = new Message.Builder().from("aaa").to("bbb").media(image).build();
    MessagingResponse messagingResponse = new MessagingResponse.Builder().message(msg).build();
    out.println(messagingResponse.toXml());
    out.flush();
  }
}
