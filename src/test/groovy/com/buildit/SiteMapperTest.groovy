package com.buildit

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ArrayBlockingQueue

import static com.buildit.WebCrawler.CRAWLING_LIMIT


class SiteMapperTest extends Specification {

	private static final String WEBSITE_ADDRESS = 'dummy-address'

	def 'correctly processes root website'() {
		given:
		Parser parser = new Parser()
		Queue<String> pendingVisitationQueue = new ArrayBlockingQueue<>(CRAWLING_LIMIT);
		Summarizer summarizer = new Summarizer(WEBSITE_ADDRESS)
		SiteMapper siteMapper = new SiteMapper(parser, summarizer, pendingVisitationQueue)

		Path path = Paths.get("src/test/resources/landing-page.html");
		BufferedReader page = Files.newBufferedReader(path)

		when:
		siteMapper.visit(page)

		then:
		siteMapper.getPendingVisitationQueue().sort() == EXPECTED_NON_VISITED_PAGES.sort()
	}

	private static final Collection<String> EXPECTED_NON_VISITED_PAGES = [
			'/',
			'/zgierz/online',
			'/zgierz/kantor',
			'/piotrkowska/kantor',
			'/przybyszewskiego/kantor',
			'/rzgowska/kantor',
			'/brzeziny/kantor',
			'/zachodnia/kantor',
			'/zgierska/kantor',
	]
}
