
package io.github.sullis.twilio.example.sms;

import com.twilio.Twilio;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "sms", value = "/")
public class SmsServlet extends HttpServlet {
  private static final String charset = "utf-8";

  private final String authToken;

  public SmsServlet() {
    this(TwilioProperties.getPassword());
  }

  public SmsServlet(String authToken) {
    this.authToken = authToken;
  }

  @Override
  public void init() {
    Twilio.init(TwilioProperties.getAccount(), authToken);
  }

  @Override
  public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    if (!SecurityUtil.isValid(authToken, req)) {
      throw new IllegalStateException("Request validation failed");
    }
    super.service(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String requestFrom = req.getParameter("From");
    String requestTo = req.getParameter("To");
    String requestBody = req.getParameter("Body");

    resp.setContentType("application/xml; charset=" + charset);
    resp.setCharacterEncoding(charset);
    PrintWriter out = resp.getWriter();
    String twiml = new TwimlBuilder().build(requestTo, requestFrom, requestBody).toXml();
    out.print(twiml);
    out.flush();
  }


}
