package io.github.sullis.twilio.example.sms;

import javax.servlet.http.HttpServletRequest;
import com.twilio.security.RequestValidator;

import java.util.HashMap;
import java.util.Map;

public class SecurityUtil {
    public static boolean isValid(final String authToken, final HttpServletRequest request) {
        final String expectedSignature = request.getHeader("X-Twilio-Signature");
        if (expectedSignature == null) {
            return false;
        }
        final RequestValidator validator = new RequestValidator(authToken);
        final StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append("?");
            url.append(request.getQueryString());
        }

        return validator.validate(url.toString(),
                buildMap(request.getParameterMap()),
                expectedSignature);
    }

    private static Map<String, String> buildMap(final Map<String, String[]> sourceMap) {
        Map<String, String> m = new HashMap<String, String>();
        for (String paramName : sourceMap.keySet()) {
            String[] values = sourceMap.get(paramName);
            if ((values != null) && (values.length > 0)) {
                m.put(paramName, values[0]);
            }
        }
        return m;
    }
}
