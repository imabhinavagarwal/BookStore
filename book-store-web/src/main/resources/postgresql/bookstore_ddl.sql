-- Run the PostgreSQL script with following command
-- psql -h <host> -U <user> -d learn_bookstore -f book-store-web/src/main/resources/postgresql/bookstore_ddl.sql

\set ON_ERROR_STOP true

CREATE TABLE IF NOT EXISTS author (
    id BIGSERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT,
    pen_name TEXT,
    date_of_birth DATE,
    date_of_demise DATE,
    nationality TEXT,
    created_at TIMESTAMP DEFAULT (now() AT TIME ZONE 'UTC'),
    last_modified_at TIMESTAMP DEFAULT (now() AT TIME ZONE 'UTC')
);
CREATE INDEX IF NOT EXISTS author_fname_idx ON author(first_name);
CREATE INDEX IF NOT EXISTS author_penname_idx ON author(pen_name);
CREATE INDEX IF NOT EXISTS author_dob_idx ON author(date_of_birth);
CREATE INDEX IF NOT EXISTS author_nationality_idx ON author(nationality);

CREATE TABLE IF NOT EXISTS genre (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    parent_genre BIGINT
);

CREATE TABLE IF NOT EXISTS book (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    genre_id BIGINT,
    date_of_publication DATE,
    created_at TIMESTAMP DEFAULT (now() AT TIME ZONE 'UTC'),
    last_modified_at TIMESTAMP DEFAULT (now() AT TIME ZONE 'UTC')
);
CREATE INDEX IF NOT EXISTS book_name_idx ON book(name);
CREATE INDEX IF NOT EXISTS book_author_id_idx ON book(author_id);
CREATE INDEX IF NOT EXISTS book_genre_id_idx ON book(genre_id);

