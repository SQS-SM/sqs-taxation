-- ==========================================
-- V1__init_payroll_schema.sql
-- Payroll System Initial Schema
-- ==========================================

-- Enable UUID extension
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

-- ==========================================
-- ROLE
-- ==========================================
CREATE TABLE public."role"
(
    id     bigserial NOT NULL,
    code   text      NOT NULL,
    "name" text      NOT NULL,
    CONSTRAINT "pk_role" PRIMARY KEY (id),
    CONSTRAINT uk_role_code UNIQUE (code)
);

-- ==========================================
-- LOOKUP
-- ==========================================
CREATE TABLE public.lookup
(
    code   varchar(100) NOT NULL,
    "type" varchar(100) NOT NULL,
    value  varchar(100) NOT NULL,
    CONSTRAINT pk_lookup PRIMARY KEY (code)
);
-- ==========================================
-- BANK DETAILS
-- ==========================================
CREATE TABLE public.bank_details
(
    id                  bigserial    NOT NULL,
    bank_name           varchar(50)  NOT NULL,
    account_holder_name varchar(200) NOT NULL,
    account_type        varchar(50)  NOT NULL,
    branch_code         varchar(50)  NOT NULL,
    branch_name         varchar(50)  NOT NULL,
    account_number      varchar(50)  NOT NULL,
    CONSTRAINT "PK_bank_details" PRIMARY KEY (id)
);

-- ==========================================
-- COUNTRY
-- ==========================================
CREATE TABLE public.country
(
    id     bigserial NOT NULL,
    code   text      NOT NULL,
    "name" text      NOT NULL,
    CONSTRAINT "PK_country" PRIMARY KEY (id)
);
CREATE UNIQUE INDEX ck_country_code ON public.country USING btree (code);

-- ==========================================
-- PROVINCE
-- ==========================================
CREATE TABLE public.province
(
    id         bigserial NOT NULL,
    code       text NULL,
    country_id int8      NOT NULL,
    "name"     text      NOT NULL,
    CONSTRAINT "PK_province" PRIMARY KEY (id),

    CONSTRAINT fk_province_country_id
        FOREIGN KEY (country_id) REFERENCES public.country (id)
);
CREATE UNIQUE INDEX ck_province_code ON public.province USING btree (code);

-- ==========================================
-- ADDRESS
-- ==========================================
CREATE TABLE public.address
(
    id          BIGSERIAL PRIMARY KEY,
    line1       TEXT      NOT NULL,
    line2       TEXT,
    suburb      text      NOT NULL,
    city        TEXT      NOT NULL,
    province_id int8 NULL,
    postal_code TEXT      NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_address_province_id
        FOREIGN KEY (province_id) REFERENCES public.province (id)
);

-- ==========================================
-- ORGANISATION
-- ==========================================
CREATE TABLE public.organisation
(
    id         BIGSERIAL PRIMARY KEY,
    guid       UUID      NOT NULL DEFAULT uuid_generate_v4(),
    name       TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT uk_organisation_guid UNIQUE (guid)
);

-- ==========================================
-- COMPANY
-- ==========================================
CREATE TABLE public.company
(
    id                           BIGSERIAL PRIMARY KEY,
    guid                         UUID      NOT NULL DEFAULT uuid_generate_v4(),
    organisation_id              BIGINT    NOT NULL,
    name                         TEXT      NOT NULL,
    registered_name              TEXT      NOT NULL,
    trading_name                 TEXT      NOT NULL,
    nature_of_business           TEXT      NOT NULL,
    company_registration_number  TEXT,
    company_logo                 TEXT,
    tax_number                   TEXT,
    sars_uif_number              TEXT,
    payer_reference_number       TEXT,
    uif_company_reference_number TEXT,
    uif_reference_number         TEXT,
    contact_number               TEXT,
    email_address                TEXT,
    fax_number                   TEXT,
    pay_sdl_ind                  BOOLEAN   NOT NULL DEFAULT FALSE,
    physical_address_id          BIGINT,
    postal_address_id            BIGINT,
    bank_details_id              BIGINT,
    created_at                   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                   TIMESTAMP,

    CONSTRAINT uk_company_guid UNIQUE (guid),
    CONSTRAINT fk_company_organisation
        FOREIGN KEY (organisation_id) REFERENCES public.organisation (id),
    CONSTRAINT fk_company_physical_address
        FOREIGN KEY (physical_address_id) REFERENCES public.address (id),
    CONSTRAINT fk_company_postal_address
        FOREIGN KEY (postal_address_id) REFERENCES public.address (id),
    CONSTRAINT fk_company_bank_details
        FOREIGN KEY (bank_details_id) REFERENCES public.bank_details (id)
);

CREATE INDEX ix_company_organisation_id ON public.company (organisation_id);

-- ==========================================
-- ACCOUNT
-- ==========================================
CREATE TABLE public.account
(
    id                  BIGSERIAL PRIMARY KEY,
    role_id             BIGINT                          NOT NULL REFERENCES public.role (id),
    company_id          BIGINT NULL,
    organisation_id     BIGINT                          NOT NULL,
    first_name          TEXT                            NOT NULL,
    last_name           TEXT                            NOT NULL,
    email_address       TEXT                            NOT NULL,
    username            TEXT                            NOT NULL UNIQUE,

    password            BYTEA                           NOT NULL,
    password_salt       BYTEA                           NOT NULL,
    password_reset_key  UUID,

    contact_number      TEXT,
    is_first_time_login BOOLEAN                         NOT NULL,

    create_date         TIMESTAMP                       NOT NULL,
    disable_date        TIMESTAMP,
    create_user_id      BIGINT REFERENCES public.account (id),

    guid                UUID DEFAULT uuid_generate_v4() NOT NULL UNIQUE,
    CONSTRAINT fk_account_company_company_id FOREIGN KEY (company_id) REFERENCES public.company (id) ON DELETE RESTRICT,
    CONSTRAINT fk_account_organisation_organisation_id FOREIGN KEY (organisation_id) REFERENCES public.organisation (id) ON DELETE RESTRICT
);
CREATE INDEX "IX_account_company_id" ON public.account USING btree (company_id);
CREATE INDEX "IX_account_organisation_id" ON public.account USING btree (organisation_id);
CREATE INDEX "IX_account_role_id" ON public.account USING btree (role_id);
-- ==========================================
-- EMPLOYEE
-- ==========================================
CREATE TABLE public.employee
(
    id                       BIGSERIAL PRIMARY KEY,
    guid                     UUID           NOT NULL DEFAULT uuid_generate_v4(),
    company_id               BIGINT         NOT NULL,
    employee_number          TEXT           NOT NULL,
    title                    varchar(20)    NOT NULL,
    initials                 varchar(20)    NOT NULL,
    first_name               TEXT           NOT NULL,
    nick_name                varchar(100)   NOT NULL,
    last_name                TEXT           NOT NULL,
    id_or_passport_number    TEXT           NOT NULL,
    date_of_birth            date           NOT NULL,
    gender                   varchar(20) NULL,
    is_south_african_citizen bool                    DEFAULT false NOT NULL,
    has_disability           bool                    DEFAULT false NOT NULL,
    disability_description   varchar(100) NULL,
    marital_status           varchar(50)    NOT NULL,
    home_language            varchar(50)    NOT NULL,
    ethnic_group             varchar(50)    NOT NULL,
    employment_type          TEXT           NOT NULL,
    basic_salary             NUMERIC(15, 2) NOT NULL,
    tax_reference_number     TEXT,
    uif_contributing         BOOLEAN        NOT NULL DEFAULT TRUE,
    email_address            varchar(500) NULL,
    work_number              varchar(20) NULL,
    home_number              varchar(20) NULL,
    mobile_number            varchar(20) NULL,
    physical_address_id      BIGINT,
    postal_address_id        BIGINT,
    bank_details_id          BIGINT         NOT NULL,
    created_at               TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_user_id           int8           NOT NULL,
    updated_at               TIMESTAMP,
    modified_user_id         int8 NULL,

    CONSTRAINT uk_employee_guid UNIQUE (guid),
    CONSTRAINT uk_employee_number UNIQUE (company_id, employee_number),
    CONSTRAINT fk_employee_company
        FOREIGN KEY (company_id) REFERENCES public.company (id),
    CONSTRAINT fk_employee_physical_address
        FOREIGN KEY (physical_address_id) REFERENCES public.address (id),
    CONSTRAINT fk_employee_postal_address
        FOREIGN KEY (postal_address_id) REFERENCES public.address (id),
    CONSTRAINT fk_employee_bank_details
        FOREIGN KEY (bank_details_id) REFERENCES public.bank_details (id),
    CONSTRAINT fk_employee_account_create_user_id FOREIGN KEY (create_user_id) REFERENCES public.account (id),
    CONSTRAINT fk_employee_account_modified_user_id FOREIGN KEY (modified_user_id) REFERENCES public.account (id)
);

CREATE INDEX ix_employee_company_id ON public.employee (company_id);

-- ==========================================
-- EMPLOYEE ALLOWANCE
-- ==========================================
CREATE TABLE public.employee_allowance
(
    id                BIGSERIAL PRIMARY KEY,
    employee_id       BIGINT         NOT NULL,
    allowance_type_id BIGINT         NOT NULL,
    amount            NUMERIC(15, 2) NOT NULL,
    effective_from    DATE           NOT NULL,
    effective_to      DATE,

    CONSTRAINT fk_employee_allowance_employee
        FOREIGN KEY (employee_id) REFERENCES public.employee (id),
    CONSTRAINT fk_employee_allowance_type
        FOREIGN KEY (allowance_type_id) REFERENCES sars.allowance_type (id)
);

CREATE INDEX ix_employee_allowance_employee_id
    ON public.employee_allowance (employee_id);

-- ==========================================
-- EMPLOYEE MEDICAL AID
-- ==========================================
CREATE TABLE public.employee_medical_aid
(
    id                    bigserial    NOT NULL,
    employee_id           int8         NOT NULL,
    medical_aid_name      varchar(100) NOT NULL,
    medical_aid_scheme    varchar(100) NOT NULL,
    medical_aid_number    varchar(20)  NOT NULL,
    number_of_dependants  int4         NOT NULL,
    is_main_member        bool         NOT NULL,
    employer_contribution money        NOT NULL,
    employee_contribution money        NOT NULL,
    create_user_id        int8         NOT NULL,
    create_date           date         NOT NULL,
    modified_user_id      int8 NULL,
    modified_date         date NULL,
    CONSTRAINT pk_employee_medical_aid PRIMARY KEY (id),
    CONSTRAINT fk_employee_medical_aid_account_create_user_id FOREIGN KEY (create_user_id) REFERENCES public.account (id),
    CONSTRAINT fk_employee_medical_aid_account_modified_user_id FOREIGN KEY (modified_user_id) REFERENCES public.account (id),
    CONSTRAINT fk_employee_medical_aid_employee_employee_id FOREIGN KEY (employee_id) REFERENCES public.employee (id)
);

-- ==========================================
-- EMPLOYEE BENEFIT
-- ==========================================
CREATE TABLE public.employee_benefit
(
    id                    bigserial NOT NULL,
    employee_id           int8      NOT NULL,
    benefit_id            int4      NOT NULL,
    employee_contribution money     NOT NULL,
    employer_contribution money     NOT NULL,
    create_user_id        int8      NOT NULL,
    create_date           date      NOT NULL,
    modified_user_id      int8 NULL,
    modified_date         date NULL,
    CONSTRAINT pk_employee_benefit PRIMARY KEY (id),
    CONSTRAINT fk_employee_benefit_account_create_user_id FOREIGN KEY (create_user_id) REFERENCES public.account (id),
    CONSTRAINT fk_employee_benefit_account_modified_user_id FOREIGN KEY (modified_user_id) REFERENCES public.account (id),
    CONSTRAINT fk_employee_benefit_benefit_benefit_id FOREIGN KEY (benefit_id) REFERENCES sars.benefit (id),
    CONSTRAINT fk_employee_benefit_employee_employee_id FOREIGN KEY (employee_id) REFERENCES public.employee (id)
);

-- ==========================================
-- PAYROLL RUN
-- ==========================================
CREATE TABLE public.payroll_run
(
    id           BIGSERIAL PRIMARY KEY,
    company_id   BIGINT     NOT NULL,
    tax_year     VARCHAR(9) NOT NULL,
    period INTEGER NOT NULL,
    processed_at TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payroll_run_company
        FOREIGN KEY (company_id) REFERENCES public.company (id)
);

CREATE INDEX ix_payroll_run_company_id
    ON public.payroll_run (company_id);

-- ==========================================
-- EMPLOYEE PAYROLL RESULT
-- ==========================================
CREATE TABLE public.employee_payroll_result
(
    id             BIGSERIAL PRIMARY KEY,
    payroll_run_id BIGINT         NOT NULL,
    employee_id    BIGINT         NOT NULL,
    gross_salary   NUMERIC(15, 2) NOT NULL,
    taxable_income NUMERIC(15, 2) NOT NULL,
    paye           NUMERIC(15, 2) NOT NULL,
    uif            NUMERIC(15, 2) NOT NULL,
    sdl            NUMERIC(15, 2) NOT NULL,
    net_salary     NUMERIC(15, 2) NOT NULL,

    CONSTRAINT fk_result_payroll_run
        FOREIGN KEY (payroll_run_id) REFERENCES public.payroll_run (id),
    CONSTRAINT fk_result_employee
        FOREIGN KEY (employee_id) REFERENCES public.employee (id)
);

CREATE INDEX ix_result_payroll_run_id
    ON public.employee_payroll_result (payroll_run_id);

CREATE INDEX ix_result_employee_id
    ON public.employee_payroll_result (employee_id);
