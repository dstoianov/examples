@if not exist log mkdir log
@echo Log file for Node 5555 > log\node_5555.log
java -jar selenium-server-standalone-2.39.0.jar -role node -nodeConfig node_5555.json -log log\node_5555.log

REM java -Dwebdriver.ie.driver=\webdriver\iedriver\iedriver_2.35.3_Win32bit.exe -Dwebdriver.chrome.driver=\webdriver\chromedriver\chromedriver_2.6_32bit.exe -cp D:\Testing\idea\Selenium-Grid-Extras\cmd\SeleniumGridExtras-1.2.3-SNAPSHOT-jar-with-dependencies.jar;\webdriver\2.37.0.jar org.openqa.grid.selenium.GridLauncher -role node -nodeConfig node_5555.json -log log\node_5555.log


REM java -jar selenium-server-standalone-2.39.0.jar -role node -hub http://localhost:4444/grid/register

/*
 1. �������� ��� java -jar selenium-server-standalone-2.30.0.jar -role hub.
 2. �������� ���� (��������, ��� ��������� 3 ���� ��� 3 ������, ������� � ����, ����� ����������� ������������, �.�. �����������):
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS -port 5556
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS -port 5557 
*/