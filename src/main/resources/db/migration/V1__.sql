CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    crated_at  datetime NULL,
    updated_at datetime NULL,
    title      VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    id             BIGINT NOT NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (id)
);

CREATE TABLE jt_mentor
(
    id BIGINT NOT NULL,
    mentor_rating DOUBLE NOT NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (id)
);

CREATE TABLE jt_ta
(
    id                 BIGINT NOT NULL,
    number_of_sessions INT    NOT NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_jt_ta PRIMARY KEY (id)
);

CREATE TABLE jt_user
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE msc_instructor
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_msc_instructor PRIMARY KEY (id)
);

CREATE TABLE msc_mentor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    mentor_rating DOUBLE NOT NULL,
    CONSTRAINT pk_msc_mentor PRIMARY KEY (id)
);

CREATE TABLE msc_ta
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    number_of_sessions INT    NOT NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_msc_ta PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    crated_at     datetime NULL,
    updated_at    datetime NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    image         VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id                 BIGINT NOT NULL,
    user_type          INT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    mentor_rating DOUBLE NOT NULL,
    number_of_sessions INT    NOT NULL,
    avg_rating DOUBLE NOT NULL,
    specialization     VARCHAR(255) NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    mentor_rating DOUBLE NOT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_ta
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    number_of_sessions INT    NOT NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK_JT_TA_ON_ID FOREIGN KEY (id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);