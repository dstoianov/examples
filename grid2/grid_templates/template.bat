:: http://rationaleemotions.wordpress.com/2012/01/23/setting-up-grid2-and-working-with-it/
set HERE=%CD%
set JAVA_HOME=%HERE%\jdk1.6.0_21
set PATH=%JAVA_HOME%\jre\bin;%JAVA_HOME%\bin;%PATH%

set SELENIUM_VERSION=2.28.0
set CHROME_VERSION=chromedriver_win_23.0.1240.0
set HUB_URL=http://localhost:4444/grid/register
set CHROME_DRIVER_LOC=%HERE%/%CHROME_VERSION%/chromedriver.exe
set IE_DRIVER_LOC=D:/selinium/IEDriverServer_Win32_2.28.0/IEDriverServer.exe

start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role hub

start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role node -Dwebdriver.chrome.driver= %CHROME_DRIVER_LOC% -hub %HUB_URL% -port 5556

start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role node -Dwebdriver.ie.driver= %IE_DRIVER_LOC% -hub %HUB_URL% -port 5557




set HERE=%CD%
set JAVA_HOME=%HERE%\jdk1.6.0_21
set PATH=%JAVA_HOME%\jre\bin;%JAVA_HOME%\bin;%PATH%
set SELENIUM_VERSION=2.17.0
set CHROME_VERSION=chrome-18.0.995.0
set HUB_URL=http://localhost:4444/grid/register
set CHROME_DRIVER_LOC=%HERE%/%CHROME_VERSION%/chromedriver.exe
start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role hub
start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role node 
-Dwebdriver.chrome.driver= %CHROME_DRIVER_LOC% -hub %HUB_URL% -port 5556