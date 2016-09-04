package com.buildit

import spock.lang.Specification

class SummarizerTest extends Specification {

	def 'routes links into relevant buckets'() {
		given:
		Summarizer summarizer = new Summarizer("http://www.some.website.co.uk")

		when:
		summarizer.process(SAMPLE_LINKS)

		then:
		summarizer.generateSummary() ==
			 '''|Website address: http://www.some.website.co.uk
				|
				|Sub-pages:
				|/zgierz/kantor
				|/piotrkowska/kantor
				|
				|Static content:
				|/css/website.css
				|/images/header_01.gif
				|
				|External links:
				|https://www.facebook.com/kantorcentpl/
				|http://www.money.pl/'''.stripMargin()
	}

	private static final Collection<String> SAMPLE_LINKS = [
			'href="/css/website.css"',
			'src="/images/header_01.gif"',
			'href="https://www.facebook.com/kantorcentpl/"',
			'href="http://www.money.pl/"',
			'href="/zgierz/kantor"',
			'href="/piotrkowska/kantor"'
	]
}
