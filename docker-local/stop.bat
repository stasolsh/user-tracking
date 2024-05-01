set ORIGIN=%cd%
cd ../../

docker compose -p awin-user-tracking -f docker-compose.yml down
cd %ORIGIN%