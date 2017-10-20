package fund.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    private static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

    private DateUtils(){

    }

    public static String getCurrentimestamp() {
        DateFormat df = new SimpleDateFormat(TIMESTAMP_FORMAT);
        return df.format(new Date());
    }
}
