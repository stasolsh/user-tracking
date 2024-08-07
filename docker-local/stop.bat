set ORIGIN=%cd%
cd ../../

docker compose -p user-tracking -f docker-compose.yml down
cd %ORIGIN%