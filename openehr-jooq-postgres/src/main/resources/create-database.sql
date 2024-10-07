CREATE ROLE "openehr-admin" WITH LOGIN PASSWORD 'athena';
CREATE ROLE "openehr-user" WITH LOGIN PASSWORD 'athena';
CREATE DATABASE "athena" ENCODING 'UTF-8' LOCALE 'C' TEMPLATE template0;
GRANT ALL PRIVILEGES ON DATABASE "athena" TO "openehr-admin";
GRANT ALL PRIVILEGES ON DATABASE "athena" TO "openehr-user";


\c "athena"
REVOKE CREATE ON SCHEMA "public" from PUBLIC;
CREATE SCHEMA IF NOT EXISTS "ehr" AUTHORIZATION "openehr-admin";
GRANT USAGE ON SCHEMA "ehr" to "openehr-user";
ALTER DEFAULT PRIVILEGES FOR USER "openehr-admin" IN SCHEMA "ehr" GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO "openehr-user";
ALTER DEFAULT PRIVILEGES FOR USER "openehr-admin" IN SCHEMA "ehr" GRANT SELECT ON SEQUENCES TO "openehr-user";

CREATE SCHEMA IF NOT EXISTS "ext" AUTHORIZATION "openehr-admin";
GRANT USAGE ON SCHEMA "ext" to "openehr-user";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA ext;

-- setup the search_patch so the extensions can be found
ALTER DATABASE "athena" SET "search_path" TO "ext";

-- ensure INTERVAL is ISO8601 encoded
ALTER DATABASE "athena" SET intervalstyle = 'iso_8601';

ALTER FUNCTION jsonb_path_query(jsonb, jsonpath, jsonb, boolean) ROWS 1;