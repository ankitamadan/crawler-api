package com.example.crawler;

import com.example.model.Games;
import com.example.service.PersistenceService;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerService extends WebCrawler {

    private static final Logger LOGGER = Logger.getLogger(CrawlerService.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");

    private List<String> listUrls = new ArrayList<String>();

    private static PersistenceService persistenceService;

    CrawlerService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public static File storageFolder;
    private static String[] crawlDomains;

    public static void configure(String[] domain, String storageFolderName) {
        crawlDomains = domain;

        storageFolder = new File(storageFolderName);
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean result = !FILTERS.matcher(href).matches();
        return result;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        String regex = "\\bbowl\\b";
        Games games = new Games();

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);

        if (page.getParseData() instanceof HtmlParseData && matcher.find()) {
            listUrls.add(url);
            games.setGameURL(url);
            persistenceService.create(games);
        }
        LOGGER.info("URLS saved successfully");
    }
}
