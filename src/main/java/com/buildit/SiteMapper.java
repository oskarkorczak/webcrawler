package com.buildit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Queue;

public class SiteMapper {

	private final Parser parser;
	private final Summarizer summarizer;
	private final Queue<String> pendingVisitationQueue;

	public SiteMapper(Parser parser, Summarizer summarizer, Queue<String> pendingVisitationQueue) {
		this.parser = parser;
		this.summarizer = summarizer;
		this.pendingVisitationQueue = pendingVisitationQueue;
	}

	public void visit(BufferedReader page) throws IOException {
		Collection<String> pageLinks = parser.parse(page);
		summarizer.process(pageLinks);
		pendingVisitationQueue.addAll(summarizer.getSubDomains());
	}

	public String summarize() {
		return summarizer.generateSummary();
	}

	public Queue<String> getPendingVisitationQueue() {
		return pendingVisitationQueue;
	}
}
