--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.5
-- Dumped by pg_dump version 9.5.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: delink
--

CREATE TABLE admin (
    id integer NOT NULL,
    use_name character varying,
    email character varying,
    password character varying
);


ALTER TABLE admin OWNER TO delink;

--
-- Name: admin_id_seq; Type: SEQUENCE; Schema: public; Owner: delink
--

CREATE SEQUENCE admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admin_id_seq OWNER TO delink;

--
-- Name: admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: delink
--

ALTER SEQUENCE admin_id_seq OWNED BY admin.id;


--
-- Name: category; Type: TABLE; Schema: public; Owner: delink
--

CREATE TABLE category (
    id integer NOT NULL,
    cat_name character varying
);


ALTER TABLE category OWNER TO delink;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: delink
--

CREATE SEQUENCE category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_id_seq OWNER TO delink;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: delink
--

ALTER SEQUENCE category_id_seq OWNED BY category.id;


--
-- Name: list; Type: TABLE; Schema: public; Owner: delink
--

CREATE TABLE list (
    id integer NOT NULL,
    list_name character varying,
    cat_id integer
);


ALTER TABLE list OWNER TO delink;

--
-- Name: list_id_seq; Type: SEQUENCE; Schema: public; Owner: delink
--

CREATE SEQUENCE list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE list_id_seq OWNER TO delink;

--
-- Name: list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: delink
--

ALTER SEQUENCE list_id_seq OWNED BY list.id;


--
-- Name: places; Type: TABLE; Schema: public; Owner: delink
--

CREATE TABLE places (
    id integer NOT NULL,
    place_name character varying,
    image1 character varying,
    image2 character varying,
    description character varying,
    costof_visit integer,
    list_id integer
);


ALTER TABLE places OWNER TO delink;

--
-- Name: places_id_seq; Type: SEQUENCE; Schema: public; Owner: delink
--

CREATE SEQUENCE places_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE places_id_seq OWNER TO delink;

--
-- Name: places_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: delink
--

ALTER SEQUENCE places_id_seq OWNED BY places.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: delink
--

ALTER TABLE ONLY admin ALTER COLUMN id SET DEFAULT nextval('admin_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: delink
--

ALTER TABLE ONLY category ALTER COLUMN id SET DEFAULT nextval('category_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: delink
--

ALTER TABLE ONLY list ALTER COLUMN id SET DEFAULT nextval('list_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: delink
--

ALTER TABLE ONLY places ALTER COLUMN id SET DEFAULT nextval('places_id_seq'::regclass);


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: delink
--

COPY admin (id, use_name, email, password) FROM stdin;
\.


--
-- Name: admin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: delink
--

SELECT pg_catalog.setval('admin_id_seq', 1, false);


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: delink
--

COPY category (id, cat_name) FROM stdin;
\.


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: delink
--

SELECT pg_catalog.setval('category_id_seq', 1, false);


--
-- Data for Name: list; Type: TABLE DATA; Schema: public; Owner: delink
--

COPY list (id, list_name, cat_id) FROM stdin;
\.


--
-- Name: list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: delink
--

SELECT pg_catalog.setval('list_id_seq', 1, false);


--
-- Data for Name: places; Type: TABLE DATA; Schema: public; Owner: delink
--

COPY places (id, place_name, image1, image2, description, costof_visit, list_id) FROM stdin;
\.


--
-- Name: places_id_seq; Type: SEQUENCE SET; Schema: public; Owner: delink
--

SELECT pg_catalog.setval('places_id_seq', 1, false);


--
-- Name: admin_pkey; Type: CONSTRAINT; Schema: public; Owner: delink
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: delink
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: list_pkey; Type: CONSTRAINT; Schema: public; Owner: delink
--

ALTER TABLE ONLY list
    ADD CONSTRAINT list_pkey PRIMARY KEY (id);


--
-- Name: places_pkey; Type: CONSTRAINT; Schema: public; Owner: delink
--

ALTER TABLE ONLY places
    ADD CONSTRAINT places_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

