package com.buildit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	private static final String HREF_LINKS_REGEX = "href=\"[a-zA-Z/:_#&\\(\\)\\-\\.]*(\\.[\\w]{2,6}|/[\\w]*)\"\\s";
	private static final String STATIC_CONTENT_REGEX = "src=\".*\\.(gif|png|js|\\?[0-9a-zA-Z/:_#&\\(\\)\\-\\.]*)\"";

	private static final Pattern LINKS_PATTERN = Pattern.compile(HREF_LINKS_REGEX);
	private static final Pattern STATIC_CONTENT_PATTERN = Pattern.compile(STATIC_CONTENT_REGEX);

	public Collection<String> parse(BufferedReader page) throws IOException {
		String line;
		Collection<String> links = new HashSet<>();

		while((line = page.readLine()) != null) {
			Matcher hrefLinksMatcher = LINKS_PATTERN.matcher(line);
			Matcher staticContentMatcher = STATIC_CONTENT_PATTERN.matcher(line);
			while(hrefLinksMatcher.find()) {
				links.add(hrefLinksMatcher.group().trim());
			}
			while(staticContentMatcher.find()){
				links.add(staticContentMatcher.group().trim());
			}
		}

		return links;
	}
}
