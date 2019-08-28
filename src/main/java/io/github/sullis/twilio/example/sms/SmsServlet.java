
package io.github.sullis.twilio.example.sms;

import com.twilio.Twilio;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "sms", value = "/")
public class SmsServlet extends HttpServlet {
  private static final String charset = "utf-8";

  @Override
  public void init() {
    Twilio.init("aaa", "bbb");
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String from = req.getParameter("From");
    String to = req.getParameter("To");
    String requestBody = req.getParameter("Body");

    resp.setContentType("application/xml; charset=" + charset);
    resp.setCharacterEncoding(charset);
    PrintWriter out = resp.getWriter();
    String twiml = new TwimlBuilder().build(to, from, requestBody).toXml();
    out.println(twiml);
    out.flush();
  }


}
