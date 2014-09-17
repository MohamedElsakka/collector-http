/**
 * 
 */
package com.norconex.collector.http.doccrawl.pipe;

import org.apache.http.client.HttpClient;

import com.norconex.collector.core.doccrawl.store.IDocCrawlStore;
import com.norconex.collector.http.crawler.HttpCrawler;
import com.norconex.collector.http.crawler.HttpCrawlerConfig;
import com.norconex.collector.http.doccrawl.HttpDocCrawl;
import com.norconex.collector.http.robot.RobotsTxt;
import com.norconex.collector.http.sitemap.ISitemapResolver;

/**
 * @author Pascal Essiembre
 *
 */
public class DocCrawlPipelineContext {

    private final HttpCrawler crawler;
    private final IDocCrawlStore docCrawlStore;
    private final HttpDocCrawl docCrawl;
    private final RobotsTxt robotsTxt;

    public DocCrawlPipelineContext(
            HttpCrawler crawler, IDocCrawlStore refStore, 
            HttpDocCrawl reference) {
        this.crawler = crawler;
        this.docCrawlStore = refStore;
        this.docCrawl = reference;
        if (!getConfig().isIgnoreRobotsTxt()) {
            this.robotsTxt = getConfig().getRobotsTxtProvider().getRobotsTxt(
                    getHttpClient(), getDocCrawl().getReference(), 
                    getConfig().getUserAgent());
        } else {
            this.robotsTxt = null;
        }
    }

    public HttpCrawler getCrawler() {
        return crawler;
    }

    public HttpCrawlerConfig getConfig() {
        return crawler.getCrawlerConfig();
    }
    
    public HttpDocCrawl getDocCrawl() {
        return docCrawl;
    }

    public HttpClient getHttpClient() {
        return crawler.getHttpClient();
    }

    public IDocCrawlStore getDocCrawlStore() {
        return docCrawlStore;
    }

    public ISitemapResolver getSitemapResolver() {
        return crawler.getSitemapResolver();
    }
    
    public RobotsTxt getRobotsTxt() {
        return robotsTxt;
    }
}
