REM java -jar selenium-server-standalone-2.39.0.jar -role node -hub http://localhost:4444/grid/register

java -jar selenium-server-standalone-2.39.0.jar -role node -nodeConfig DefaultNode

/*
 1. �������� ��� java -jar selenium-server-standalone-2.30.0.jar -role hub.
 2. �������� ���� (��������, ��� ��������� 3 ���� ��� 3 ������, ������� � ����, ����� ����������� ������������, �.�. �����������):
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS -port 5556
 java -jar selenium-server-standalone-2.30.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox,version=19.0,platform=WINDOWS -port 5557 
*/

REM java -jar selenium-server-standalone-2.39.0.jar -role node  -hub http://14.67.204.101:4444/grid/register -browser browserName=chrome -port 5556