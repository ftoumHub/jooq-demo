ALTER SYSTEM SET max_connections = 1000;
ALTER SYSTEM RESET shared_buffers;
CREATE DATABASE jooqdemo;
CREATE USER jooqdemo WITH PASSWORD 'jooqdemo';
GRANT ALL PRIVILEGES ON DATABASE "jooqdemo" to jooqdemo;