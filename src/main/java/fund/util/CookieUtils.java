package fund.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public final class CookieUtils {
    private static final String SEMICOLON = ";";
    private static final String EQUAL_SIGN = "=";

    private CookieUtils() {

    }

    public static Map<String, String> parse(String cookie) {
        if (StringUtils.isNotBlank(cookie)) {
            return Arrays.asList(cookie.split(SEMICOLON)).stream().map(item -> item.split(EQUAL_SIGN)).collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
        }
        return Collections.EMPTY_MAP;
    }

}
