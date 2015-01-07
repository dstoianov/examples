@echo on
mvn clean -Dtest=SampleTest test & mvn site org.reportyng:reporty-ng:1.2:reportyng
REM mvn clean -DsuiteXmlFile=reporty-ng-suite.xml test

REM mvn clean -Dtest=SampleTest test site org.reportyng:reporty-ng:1.2:reportyng