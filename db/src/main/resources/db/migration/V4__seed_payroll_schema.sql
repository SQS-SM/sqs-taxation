INSERT INTO public.country
    (code, "name")
VALUES ('ZA', 'South Africa');

INSERT INTO public.province
    (country_id, code, name)
VALUES ((SELECT id FROM public.country WHERE code = 'ZA'), 'EC', 'Eastern Cape'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'FS', 'Free State'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'GP', 'Gauteng'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'LP', 'Limpopo'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'KZN', 'KwaZulu Natal'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'MP', 'Mpumalanga'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'NW', 'North West'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'NC', 'Northern Cape'),
       ((SELECT id FROM public.country WHERE code = 'ZA'), 'WC', 'Western Cape');

INSERT INTO public."role"
    (code, "name")
VALUES ('SUPERADMIN', 'Super Admin Role');
INSERT INTO public."role"
    (code, "name")
VALUES ('ADMIN', 'Admin');

INSERT INTO public.lookup
    (code, "type", value)
VALUES ('MR', 'TITLE', 'MR');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('MRS', 'TITLE', 'MRS');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('MISS', 'TITLE', 'MISS');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('DR', 'TITLE', 'DR');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('HON', 'TITLE', 'HON');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('MARRIED', 'MARITAL_STATUS', 'MARRIED');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('DIVORCED', 'MARITAL_STATUS', 'DIVORCED');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('WIDOWED', 'MARITAL_STATUS', 'WIDOWED');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('African', 'ETHNIC_GROUP', 'African');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('White', 'ETHNIC_GROUP', 'White');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('Coloured', 'ETHNIC_GROUP', 'Coloured');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('Asian', 'ETHNIC_GROUP', 'Asian');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('Indian', 'ETHNIC_GROUP', 'Indian');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('ISIZULU', 'LANGUAGE', 'ISIZULU');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('ISIXHOSA', 'LANGUAGE', 'ISIXHOSA');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('ISINDEBELE', 'LANGUAGE', 'ISINDEBELE');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('SISWATI', 'LANGUAGE', 'SISWATI');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('SESOTHO', 'LANGUAGE', 'SESOTHO');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('SEPEDI', 'LANGUAGE', 'SEPEDI');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('XITSONGA', 'LANGUAGE', 'XITSONGA');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('SETSWANA', 'LANGUAGE', 'SETSWANA');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('ENGLISH', 'LANGUAGE', 'ENGLISH');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('AFRIKAANS', 'LANGUAGE', 'AFRIKAANS');
INSERT INTO public.lookup
    (code, "type", value)
VALUES ('TSHIVENDA', 'LANGUAGE', 'TSHIVENDA');