ALTER SYSTEM SET max_connections = 1000;
ALTER SYSTEM RESET shared_buffers;
--CREATE DATABASE sakilapg;
--CREATE USER sakilapg WITH PASSWORD 'sakilapg';
GRANT ALL PRIVILEGES ON DATABASE "sakilapg" to sakilapg;