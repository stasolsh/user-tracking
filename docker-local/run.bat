xcopy /s /y .\docker-compose.yml ..\..\

set ORIGIN=%cd%
cd ../../
set ROOT=%cd%

docker compose -p awin-user-tracking -f docker-compose.yml stop awin-user-tracking

cd %ROOT%/awin-user-tracking
call mvn -T 1C clean install -DskipTests=true

cd %ROOT%/
docker compose -p awin-user-tracking -f docker-compose.yml up --build -d mongodb mongodb-management --no-deps awin-user-tracking

cd %ORIGIN%