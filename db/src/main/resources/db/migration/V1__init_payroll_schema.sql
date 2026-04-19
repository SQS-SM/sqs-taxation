-- DROP SCHEMA sars;

CREATE TABLE sars.allowance_type
(
    id             serial4      NOT NULL,
    allowance_code varchar(30)  NOT NULL,
    irp5_code      varchar(10)  NOT NULL,
    description    varchar(150) NOT NULL,
    CONSTRAINT pk_allowance_type PRIMARY KEY (id),
    CONSTRAINT uk_allowance_code UNIQUE (allowance_code)
);

CREATE TABLE sars.benefit
(
    id                    serial4      NOT NULL,
    tax_year              VARCHAR(4)   NOT NULL,
    code                  varchar(30)  NOT NULL,
    irp5_code             varchar(10)  NOT NULL,
    name                  varchar(150) NOT NULL,
    taxable               BOOLEAN      NOT NULL DEFAULT TRUE,
    contribution_employee NUMERIC(15, 2),
    contribution_employer NUMERIC(15, 2),
    cap                   NUMERIC(15, 2),
    description           TEXT,
    created_at            TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at            TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_benefit PRIMARY KEY (id),
    CONSTRAINT uk_benefit_code UNIQUE (code)
);

CREATE TABLE sars.allowance_tax_rate
(
    id                         bigserial         NOT NULL,
    allowance_type_id          int4              NOT NULL,
    tax_year                   varchar(4)        NOT NULL,
    taxable_portion_percentage numeric(5, 2)     NOT NULL,
    effective_from             date              NOT NULL,
    effective_to               date NULL,
    active                     bool DEFAULT true NOT NULL,
    CONSTRAINT chk_taxable_percentage CHECK (((taxable_portion_percentage >= (0)::numeric) AND
                                              (taxable_portion_percentage <= (100)::numeric))),
    CONSTRAINT pk_allowance_tax_rate PRIMARY KEY (id),
    CONSTRAINT uk_allowance_year UNIQUE (allowance_type_id, tax_year),
    CONSTRAINT fk_allowance_type FOREIGN KEY (allowance_type_id) REFERENCES sars.allowance_type (id)
);

CREATE TABLE sars.income_tax
(
    id                     serial4    NOT NULL,
    "year"                 varchar(4) NOT NULL,
    minimum_income         numeric(38, 2) NULL,
    maximum_income         numeric(38, 2) NULL,
    sliding_scale          numeric(38, 2) NULL,
    minimum_taxable_amount numeric(38, 2) NULL,
    tax_percentage         numeric    NOT NULL,
    CONSTRAINT pk_income_tax PRIMARY KEY (id)
);

CREATE TYPE sars.medical_scheme_tax_credit_enum AS ENUM (
	'PRIMARY_MEMBER',
	'PRIMARY_AND_FIRST_DEPENDANT',
	'ADDITIONAL_DEPENDANT');

CREATE TABLE sars.medical_scheme_tax_credit_rate
(
    id               bigserial                           NOT NULL,
    tax_year         int4                                NOT NULL,
    credit_type_code sars.medical_scheme_tax_credit_enum NOT NULL,
    monthly_amount   numeric(38, 2)                      NOT NULL,
    effective_from   date                                NOT NULL,
    effective_to     date NULL,
    CONSTRAINT chk_effective_dates CHECK (((effective_to IS NULL) OR (effective_to > effective_from))),
    CONSTRAINT medical_scheme_tax_credit_rate_monthly_amount_check CHECK ((monthly_amount >= (0)::numeric)),
    CONSTRAINT medical_scheme_tax_credit_rate_pkey PRIMARY KEY (id),
    CONSTRAINT medical_scheme_tax_credit_rate_tax_year_check CHECK ((tax_year >= 2000)),
    CONSTRAINT uq_tax_year_credit_type UNIQUE (tax_year, credit_type_code)
);

CREATE TABLE sars.pension_fund
(
    id                          serial4        NOT NULL,
    tax_year                    varchar(4)     NOT NULL,
    annual_remuneration_percent numeric(5, 2)  NOT NULL,
    maximum_amount              numeric(12, 2) NOT NULL,
    CONSTRAINT pension_fund_pkey PRIMARY KEY (id)
);

CREATE TABLE sars.tax_rebate
(
    id                 serial4      NOT NULL,
    "year"             varchar(4)   NOT NULL,
    minimum_age        int4         NOT NULL,
    maximum_age        int4         NOT NULL,
    rebate_amount      numeric(38, 2) NULL,
    rebate_description varchar(500) NOT NULL,
    CONSTRAINT pk_tax_rebate PRIMARY KEY (id)
);

CREATE TABLE sars.tax_threshold
(
    id               serial4    NOT NULL,
    "year"           varchar(4) NOT NULL,
    minimum_age      int4       NOT NULL,
    maximum_age      int4       NOT NULL,
    threshold_amount money      NOT NULL,
    CONSTRAINT pk_tax_threshold PRIMARY KEY (id)
);

CREATE TABLE sars.uif
(
    id                   serial4                          NOT NULL,
    effective_from       date                             NOT NULL,
    effective_to         date NULL,
    employee_rate        numeric                          NOT NULL,
    employer_rate        numeric(5, 4)  DEFAULT 0.01      NOT NULL,
    monthly_earnings_cap numeric(10, 2) DEFAULT 17712.00  NOT NULL,
    annual_earnings_cap  numeric(12, 2) DEFAULT 212544.00 NOT NULL,
    active               bool           DEFAULT true      NOT NULL,
    CONSTRAINT pk_uif PRIMARY KEY (id)
);
