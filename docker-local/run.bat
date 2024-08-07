xcopy /s /y .\docker-compose.yml ..\..\

set ORIGIN=%cd%
cd ../../
set ROOT=%cd%

docker compose -p user-tracking -f docker-compose.yml stop user-tracking

cd %ROOT%/user-tracking
call mvn -T 1C clean install -DskipTests=true

cd %ROOT%/
docker compose -p user-tracking -f docker-compose.yml up --build -d mongodb mongodb-management --no-deps user-tracking

cd %ORIGIN%