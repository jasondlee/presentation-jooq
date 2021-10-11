#!/bin/bash

psql -U postgres -d template1 << EOF
drop database if exists jooq_demo;
drop user if exists jooq_demo;
create user jooq_demo with encrypted password 'jooq_demo';
create database jooq_demo owner jooq_demo;
grant all privileges on database jooq_demo to jooq_demo ;
EOF

psql -U jooq_demo -d jooq_demo -f jooq_demo.sql
