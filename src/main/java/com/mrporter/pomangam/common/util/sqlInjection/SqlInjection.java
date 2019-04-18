package com.mrporter.pomangam.common.util.sqlInjection;

import com.mrporter.pomangam.common.util.time.CustomTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
final public class SqlInjection {

    private static final Logger logger = LoggerFactory.getLogger(SqlInjection.class);

    /* 특수문자 공백 처리 */
    static final Pattern SpecialChars = Pattern.compile("['\"\\-#()@;=*/+<>]");
    /* sql예약어 처리 */
    static final String regex = "(union|select|from|where|substring|"
            + "information_schema|sysobjects|table_schema|declare|update|join|drop|insert|delete)";
    static final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    public static boolean isSQLInjection(String userInput) {
        if(userInput == null || userInput.length() == 0) return false;

        Matcher matcher = pattern.matcher(userInput);
        userInput = SpecialChars.matcher(userInput).replaceAll("");

        if(matcher.find()){
            log("[SQL INJECTION] " + userInput);
            return true;
        }
        else
            return false;
    }

    public static boolean isSQLInjection(String...userInputs) {
        for(String arg : userInputs) {
            if(isSQLInjection(arg))
                return true;
        }
        return false;
    }

    static void log(String text) {
        long time = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("X-FORWARDED-FOR");
        int port = 0;
        if(ip == null) {
            ip = request.getRemoteAddr();
            port = request.getRemotePort();
        }
        String curDateTime = CustomTime.curDateTime();

        logger.info(ip + ":" + port + " - " + text + " - " + curDateTime);
    }
}