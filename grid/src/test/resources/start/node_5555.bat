::echo off
@set port=5555
@if not exist log mkdir log
@echo Log file for Node %port% > log\node_%port%.log
java -jar selenium-server-standalone-2.43.1.jar -role node -nodeConfig node_%port%.json -Dwebdriver.ie.driver=IEDriverServer.exe -Dwebdriver.chrome.driver=chromedriver.exe -log log\node_%port%.log

REM java -Dwebdriver.ie.driver=\webdriver\iedriver\iedriver_2.35.3_Win32bit.exe -Dwebdriver.chrome.driver=\webdriver\chromedriver\chromedriver_2.6_32bit.exe -cp D:\Testing\idea\Selenium-Grid-Extras\cmd\SeleniumGridExtras-1.2.3-SNAPSHOT-jar-with-dependencies.jar;\webdriver\2.37.0.jar org.openqa.grid.selenium.GridLauncher -role node -nodeConfig node_5555.json -log log\node_5555.log
rem if not exist log mkdir log

REM java -jar selenium-server-standalone-2.39.0.jar -role node -hub http://localhost:4444/grid/register

/*
 1. Запускаю хаб java -jar selenium-server-standalone-2.30.0.jar -role hub.
 2. Запускаю ноды (например, мне требуется 3 узла для 3 тестов, которые я хочу, чтобы выполнялись одновременно, т.е. параллельно):
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS -port 5556
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS -port 5557 
*/