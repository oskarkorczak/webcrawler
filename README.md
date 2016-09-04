Web Crawler
===========

Building the project
====================
The project is written in Gradle and it contains Gradle wrapper embedded,
so there is no need to install Gradle in your machine.

In order to build the project, please run below command:
./gradlew clean build

Running the project
===================
In order to run project, please either run below Java main class:
com.buildit.WebCrawler.main

or try to kick in the Spock system test running deliberately against live website:
com.buildit.WebCrawlerSystemTest.crawls live website

Assumptions
===========
1. No external libs for handling html/xml are used

Trade-offs & TBD items
=====================
1. Add/improve exception handling
2. Calibrate crawling limit defined in com.buildit.WebCrawler
3. Parsing REGEX could be improved or replaced with a proper library
4. Summarizer could be more intelligent and recognize more useful static contents etc.
5. Increase test coverage around edge case scenarios say network issues etc.