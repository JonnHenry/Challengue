CREATE TABLE IF NOT EXISTS public.roles
(
    id integer NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    created_at timestamp without time zone NOT NULL,
    is_active boolean DEFAULT true,
    rol character varying(255) COLLATE pg_catalog."default" NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT uniquerol UNIQUE (rol)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    birth_date timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean DEFAULT true,
    names character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surnames character varying(255) COLLATE pg_catalog."default" NOT NULL,
    telephone character varying(255) COLLATE pg_catalog."default" NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uniqueemail UNIQUE (email),
    CONSTRAINT uniqueusername UNIQUE (user_name)
);

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    rol_id integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.users_vaccines
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    vaccine_id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    number_doses integer,
    updated_at timestamp without time zone NOT NULL,
    vaccination_date timestamp without time zone,
    CONSTRAINT users_vaccines_pkey PRIMARY KEY (user_id, vaccine_id)
);

CREATE TABLE IF NOT EXISTS public.vaccines
(
    id integer NOT NULL DEFAULT nextval('vaccines_id_seq'::regclass),
    vaccine_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone NOT NULL,
    is_active boolean DEFAULT true,
    updated_at timestamp without time zone NOT NULL,
    CONSTRAINT vaccines_pkey PRIMARY KEY (id),
    CONSTRAINT uniquevaccinename UNIQUE (vaccine_name)
);

ALTER TABLE IF EXISTS public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.users_roles
    ADD CONSTRAINT fkrq701yrxk88ea15i2oesc7fxk FOREIGN KEY (rol_id)
    REFERENCES public.roles (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.users_vaccines
    ADD CONSTRAINT fk6ali5i43sokhmbqm468iu9x5o FOREIGN KEY (vaccine_id)
    REFERENCES public.vaccines (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.users_vaccines
    ADD CONSTRAINT fkk4ofxb5eyx7ohyqiu2xbni4uc FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;