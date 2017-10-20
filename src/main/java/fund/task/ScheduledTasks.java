package fund.task;


import fund.crawler.FundListCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private FundListCrawler fundListCrawler;

    @Scheduled(cron = "0 37 0 ? * *")
    public void fetchFundList() {
        logger.info("Start to fetch fund list.");
        try {
            fundListCrawler.run();
            logger.info("Successfully fetch fund list.");
        } catch (IOException e) {
            logger.error("Fail to fetch fund list", e);
        }
    }

}
