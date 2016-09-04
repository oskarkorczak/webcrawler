package com.buildit;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Summarizer {

	private final String websiteAddress;
	private final Collection<String> subDomains;
	private final Collection<String> staticContent;
	private final Collection<String> externalUrls;

	public Summarizer(String websiteAddress) {
		this.websiteAddress = websiteAddress;
		this.subDomains = new LinkedHashSet<>();
		this.staticContent = new LinkedHashSet<>();
		this.externalUrls = new LinkedHashSet<>();
	}

	public Collection<String> getSubDomains() {
		return new LinkedHashSet<>(subDomains);
	}

	public void process(Collection<String> links) {
		for(String link: links) {
			if(link.contains("gif") || link.contains("png") || link.contains("js") || link.contains("css")) {
				staticContent.add(strip(link));
			} else if (link.contains("http") || link.contains("www.")) {
				externalUrls.add(strip(link));
			} else {
				subDomains.add(strip(link));
			}
		}
	}

	public String generateSummary() {
		StringBuilder buffer = new StringBuilder("Website address: ");
		buffer.append(websiteAddress);
		buffer.append("\n\n");
		buffer.append("Sub-pages:\n");
		for(String subdomainLink: subDomains) {
			buffer.append(subdomainLink);
			buffer.append("\n");
		}
		buffer.append("\n");
		buffer.append("Static content:\n");
		for(String staticLink: staticContent) {
			buffer.append(staticLink);
			buffer.append("\n");
		}
		buffer.append("\n");
		buffer.append("External links:\n");
		for(String externalLink: externalUrls) {
			buffer.append(externalLink);
			buffer.append("\n");
		}

		return buffer.toString().trim();
	}

	private String strip(String linkEnding) {
		String emptyChar = "";
		String noPrefixHref = linkEnding.replace("href=\"", emptyChar);
		String noPrefixSrc = noPrefixHref.replace("src=\"", emptyChar);
		return noPrefixSrc.replace("\"", emptyChar);
	}
}
