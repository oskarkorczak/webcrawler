package com.buildit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class WebCrawler {

	static final int CRAWLING_LIMIT = 10;

	private final String website;
	private final Queue<String> pendingVisitationQueue;
	private final SiteMapper siteMapper;

	public static void main(String[] args) throws IOException {
		String website = "http://www.kantor-cent.pl";
		Queue<String> pendingVisitationQueue = new ArrayBlockingQueue<>(CRAWLING_LIMIT);
		Parser parser = new Parser();
		Summarizer summarizer = new Summarizer(website);
		SiteMapper siteMapper = new SiteMapper(parser, summarizer, pendingVisitationQueue);
		WebCrawler webCrawler = new WebCrawler(website, siteMapper, pendingVisitationQueue);

		String summary = webCrawler.crawl();

		System.out.println(summary);
	}

	public WebCrawler(String website, SiteMapper siteMapper, Queue<String> pendingVisitationQueue) {
		this.website = website;
		this.siteMapper = siteMapper;
		this.pendingVisitationQueue = pendingVisitationQueue;
		this.pendingVisitationQueue.add(website);
	}

	public String crawl() throws IOException {
		try {
			while (!pendingVisitationQueue.isEmpty()) {
				String currentPageLink = pendingVisitationQueue.poll();
				BufferedReader currentPage = openStream(currentPageLink);
				siteMapper.visit(currentPage);
			}

			return siteMapper.summarize();
		} catch (IllegalStateException ise) {
			return siteMapper.summarize();
		}
	}

	private BufferedReader openStream(String link) throws IOException {
		if(!link.contains(website)) {
			link = website + link;
		}
		InputStream inputStream = new URL(link).openStream();
		return new BufferedReader(new InputStreamReader(inputStream));
	}
}
