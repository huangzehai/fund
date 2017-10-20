package fund.crawler;

import fund.constant.Client;
import fund.model.Fund;
import fund.service.FundService;
import fund.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FundListCrawler implements Crawler {

    private static final String URL = "http://fund.eastmoney.com/js/fundcode_search.js?v=%s";
    private static final String REFERER = "http://fund.eastmoney.com/data/fundranking.html";

    private Logger logger = LoggerFactory.getLogger(FundListCrawler.class);
    private static final int SUCCESS = 200;

    @Autowired
    private FundService fundService;

    public void run() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(String.format(URL, DateUtils.getCurrentimestamp()));
        // add request header
        request.addHeader("User-Agent", Client.USER_AGENT);
        request.addHeader("Referer", REFERER);
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == SUCCESS) {
            String responseBody = getResponseBody(response);
            if (StringUtils.isNotBlank(responseBody)) {
                String fundList = StringUtils.substringBetween(responseBody, "[[", "]]");
                if (StringUtils.isNotBlank(fundList)) {
                    List<Fund> funds = Arrays.asList(fundList.split("],\\[")).stream().map(item -> item.split(",")).map(item -> {
                        Fund fund = new Fund();
                        fund.setFundCode(value(item[0]));
                        fund.setFundAbbr(value(item[1]));
                        fund.setFundName(value(item[2]));
                        fund.setFundType(value(item[3]));
                        fund.setFundPinyin(value(item[4]));
                        return fund;
                    }).collect(Collectors.toList());
                    fundService.addFunds(funds);
                }
            }
        } else {
            logger.error("Fail to get fund list as status code is {}", response.getStatusLine().getStatusCode());
        }


    }

    private String getResponseBody(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private String value(String value) {
        return value.replaceAll("\"", StringUtils.EMPTY);
    }

}
