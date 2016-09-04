package com.buildit

import spock.lang.Specification

import java.util.concurrent.ArrayBlockingQueue

import static com.buildit.WebCrawler.CRAWLING_LIMIT

class WebCrawlerSystemTest extends Specification {

	def 'crawls live website'() {
		given:
		String website = "http://www.kantor-cent.pl"

		Parser parser = new Parser()
		Summarizer summarizer = new Summarizer(website)
		Queue<String> pendingVisitationQueue = new ArrayBlockingQueue<>(CRAWLING_LIMIT);
		SiteMapper siteMapper = new SiteMapper(parser, summarizer, pendingVisitationQueue)
		WebCrawler webCrawler = new WebCrawler(website, siteMapper, pendingVisitationQueue)

		when:
		String result = webCrawler.crawl()

		then:
		result == '''Website address: http://www.kantor-cent.pl
				|
				|Sub-pages:
				|/piotrkowska/kantor
				|/zgierska/kantor
				|/zachodnia/kantor
				|/
				|/przybyszewskiego/kantor
				|/brzeziny/kantor
				|/zgierz/online
				|/zgierz/kantor
				|/rzgowska/kantor
				|/piotrkowska/onas
				|/piotrkowska/notowania
				|/piotrkowska/aktualnosci
				|/kantory
				|/piotrkowska/kontakt
				|
				|Static content:
				|http://static1.money.pl/i/loga/moneypl_pp2.gif
				|/images/centOnline2.gif
				|/css/style-stock.css
				|/js/jquery-1.7.1.min.js
				|/images/header_01.gif
				|/images/mapa_kantorow.gif
				|/css/website.css
				|
				|External links:
				|https://www.facebook.com/kantorcentpl/
				|http://www.money.pl/
				|http://www.libert.pl'''.stripMargin()
	}
}
