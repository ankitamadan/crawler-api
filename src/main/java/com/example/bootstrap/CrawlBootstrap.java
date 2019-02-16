package com.example.bootstrap;

import com.example.crawler.CrawlerService;
import com.example.crawler.CrawlerServiceFactory;
import com.example.service.PersistenceService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CrawlBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = Logger.getLogger(CrawlBootstrap.class);

    String[] crawlDomains = {"http://espnevents.com"};
    String crawlStorageFolder = "root";

    @Autowired
    PersistenceService persistenceService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try {
            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(crawlStorageFolder);
            config.setIncludeBinaryContentInCrawling(true);
            config.setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            config.setResumableCrawling(true);

            PageFetcher fetcher = new PageFetcher(config);
            RobotstxtConfig robotsConfig = new RobotstxtConfig();
            robotsConfig.setEnabled(false);
            RobotstxtServer robotsSvr = new RobotstxtServer(robotsConfig, fetcher);

            CrawlController controller = new CrawlController(config, fetcher, robotsSvr);
            for (String domain : crawlDomains) {
                controller.addSeed(domain);
            }

            CrawlerService.configure(crawlDomains, crawlStorageFolder);
            CrawlerServiceFactory factory = new CrawlerServiceFactory(persistenceService);
            controller.startNonBlocking(factory, 1);


        } catch (Exception e) {
            LOGGER.warn("Crawler failed to bootstrap");
        }

    }
}
