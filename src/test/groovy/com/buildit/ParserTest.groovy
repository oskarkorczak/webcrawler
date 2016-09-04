package com.buildit

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ParserTest extends Specification {

	def 'correctly parses single web page'() {
		given:
		Path path = Paths.get("src/test/resources/landing-page.html");
		BufferedReader singlePage = Files.newBufferedReader(path);
		Parser parser = new Parser()

		when:
		Collection<String> links = parser.parse(singlePage)

		then:
		links.sort() == EXPECTED_LINKS.sort()
	}

	private static final Collection<String> EXPECTED_LINKS = [
			'href="/css/website.css"',
			'href="/css/style-stock.css"',
			'src="/js/jquery-1.7.1.min.js"',
			'href="/"',
			'src="/images/header_01.gif"',
			'href="https://www.facebook.com/kantorcentpl/"',
			'href="http://www.money.pl/"',
			'href="/zgierz/online"',
			'src="/images/centOnline2.gif"',
			'src="/images/mapa_kantorow.gif"',
			'href="/zgierz/kantor"',
			'href="/piotrkowska/kantor"',
			'href="/przybyszewskiego/kantor"',
			'href="/rzgowska/kantor"',
			'href="/brzeziny/kantor"',
			'href="/zachodnia/kantor"',
			'href="/zgierska/kantor"',
			'src="http://static1.money.pl/i/loga/moneypl_pp2.gif"',
			'href="http://www.libert.pl"',
	]
}
