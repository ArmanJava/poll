Описание проекта:

База данных:
sudo -u postgres psql
CREATE DATABASE polls  WITH OWNER = postgres ENCODING = 'UTF8';
CREATE USER polls WITH password 'polls';
GRANT ALL ON DATABASE polls TO polls;

АПИ:
http://localhost:8081/swagger-ui.html

Докер:
docker build ./ -t springbootapp
docker-compose -f docker-compose.yml up