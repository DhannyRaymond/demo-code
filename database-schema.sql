--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4 (Debian 16.4-1.pgdg120+1)
-- Dumped by pg_dump version 16.6 (Homebrew)

-- Started on 2024-12-12 18:18:33 WIB

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3387 (class 1262 OID 21172)
-- Name: nutech_integrasi; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE nutech_integrasi WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE nutech_integrasi OWNER TO postgres;

\connect nutech_integrasi

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3388 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 21378)
-- Name: balance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.balance (
    id character varying(255) NOT NULL,
    balance integer,
    membership_id character varying(255) NOT NULL
);


ALTER TABLE public.balance OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 21244)
-- Name: banner; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.banner (
    id character varying(255) NOT NULL,
    banner_image character varying(100),
    banner_name character varying(100),
    description character varying(100)
);


ALTER TABLE public.banner OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 21225)
-- Name: membership; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membership (
    id character varying(255) NOT NULL,
    email character varying(100),
    first_name character varying(100),
    last_name character varying(100),
    password character varying(255) NOT NULL,
    profile_image character varying(256)
);


ALTER TABLE public.membership OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 21255)
-- Name: partner; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partner (
    id character varying(255) NOT NULL,
    service_code character varying(100),
    service_icon character varying(100),
    service_name character varying(100),
    service_tariff integer
);


ALTER TABLE public.partner OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 21405)
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    id character varying(255) NOT NULL,
    created_on timestamp(6) with time zone,
    description character varying(100),
    invoice_number character varying(100),
    amount integer,
    transaction_type character varying(50),
    membership_id character varying(255) NOT NULL
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 21180)
-- Name: uuid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uuid
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.uuid OWNER TO postgres;

--
-- TOC entry 3380 (class 0 OID 21378)
-- Dependencies: 219
-- Data for Name: balance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.balance (id, balance, membership_id) FROM stdin;
\.


--
-- TOC entry 3378 (class 0 OID 21244)
-- Dependencies: 217
-- Data for Name: banner; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.banner (id, banner_image, banner_name, description) FROM stdin;
1	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Banner 1	Lerem Ipsum Dolor sit amet
2	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Banner 2	Lerem Ipsum Dolor sit amet
3	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Banner 3	Lerem Ipsum Dolor sit amet
4	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Banner 4	Lerem Ipsum Dolor sit amet
5	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Banner 5	Lerem Ipsum Dolor sit amet
6	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Banner 6	Lerem Ipsum Dolor sit amet
\.


--
-- TOC entry 3377 (class 0 OID 21225)
-- Dependencies: 216
-- Data for Name: membership; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membership (id, email, first_name, last_name, password, profile_image) FROM stdin;
\.


--
-- TOC entry 3379 (class 0 OID 21255)
-- Dependencies: 218
-- Data for Name: partner; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partner (id, service_code, service_icon, service_name, service_tariff) FROM stdin;
1	PAJAK	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Pajak PBB	40000
2	PLN	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Listrik	10000
3	PDAM	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	PDAM Berlangganan	40000
4	PULSA	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Pulsa	40000
5	PGN	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	PGN	50000
6	MUSIK	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Musik Berlangganan	50000
7	TV	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	TV Berlangganan	50000
8	PAKET_DATA	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Paket Data	50000
9	VOUCHER_GAME	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Voucher Game	100000
10	VOUCHER_MAKANAN	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Voucher Makanan	100000
11	QURBAN	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Qurban	200000
12	ZAKAT	/Users/dhannyraymond/Documents/SourceCode/JAVA/PLAYGROUND/demo/src/main/upload/dummy.jpg	Zakat	300000
\.


--
-- TOC entry 3381 (class 0 OID 21405)
-- Dependencies: 220
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (id, created_on, description, invoice_number, amount, transaction_type, membership_id) FROM stdin;
\.


--
-- TOC entry 3389 (class 0 OID 0)
-- Dependencies: 215
-- Name: uuid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uuid', 1, true);


--
-- TOC entry 3226 (class 2606 OID 21384)
-- Name: balance balance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.balance
    ADD CONSTRAINT balance_pkey PRIMARY KEY (id);


--
-- TOC entry 3222 (class 2606 OID 21250)
-- Name: banner banner_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.banner
    ADD CONSTRAINT banner_pkey PRIMARY KEY (id);


--
-- TOC entry 3220 (class 2606 OID 21231)
-- Name: membership membership_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membership
    ADD CONSTRAINT membership_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 21261)
-- Name: partner partner_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partner
    ADD CONSTRAINT partner_pkey PRIMARY KEY (id);


--
-- TOC entry 3230 (class 2606 OID 21411)
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


--
-- TOC entry 3228 (class 2606 OID 21386)
-- Name: balance ukx0u6cnqcp31avdktj9nyp232; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.balance
    ADD CONSTRAINT ukx0u6cnqcp31avdktj9nyp232 UNIQUE (membership_id);


--
-- TOC entry 3232 (class 2606 OID 21412)
-- Name: transaction fkgjyoai9ki88avy714wo7x5i1o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fkgjyoai9ki88avy714wo7x5i1o FOREIGN KEY (membership_id) REFERENCES public.membership(id);


--
-- TOC entry 3231 (class 2606 OID 21387)
-- Name: balance fkofism3ea7blpvt83gtyhqandd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.balance
    ADD CONSTRAINT fkofism3ea7blpvt83gtyhqandd FOREIGN KEY (membership_id) REFERENCES public.membership(id);


-- Completed on 2024-12-12 18:18:33 WIB

--
-- PostgreSQL database dump complete
--

