#!/bin/bash

psql -U postgres -d template1 << EOF
drop database if exists jooq_demo;
drop user if exists jooq_demo;
create user jooq_demo with encrypted password 'jooq_demo';
create database jooq_demo owner jooq_demo;
grant all privileges on database jooq_demo to jooq_demo ;
EOF

    #postgres-sakila-delete-data.sql \
    #postgres-sakila-drop-objects.sql \

psql -q -U postgres -d jooq_demo -f postgres-sakila-schema.sql

psql -q -U postgres -d jooq_demo << EOF
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO jooq_demo;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO jooq_demo;
EOF

psql -q -U postgres -d jooq_demo -f postgres-sakila-insert-data.sql


