package fund.constant;

import java.util.HashMap;
import java.util.Map;

public final class RequestHeaders {
    private RequestHeaders() {

    }

    public static Map<String, String> get() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding:","gzip, deflate");
        headers.put("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6");
        headers.put("Cache-Control","max-age=0");
        headers.put("Connection","keep-alive");
        headers.put("If-Modified-Since","Fri, 20 Oct 2017 13:38:21 GMT");
//        headers.put("Host","");
        headers.put("Upgrade-Insecure-Requests","1");
        return headers;
    }
}
