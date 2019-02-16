package com.example.crawler;

import com.example.service.PersistenceService;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class CrawlerServiceFactory implements CrawlController.WebCrawlerFactory {

    PersistenceService persistenceService;

    public CrawlerServiceFactory(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public WebCrawler newInstance() throws Exception {
        return new CrawlerService(persistenceService);
    }
}

