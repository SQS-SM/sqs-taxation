-- ==========================================
-- V2__seed_allowance_types.sql
-- Seed SARS-aligned Allowance Types
-- ==========================================

INSERT INTO sars.allowance_type
    (allowance_code, description, irp5_code)
VALUES ('TRAVEL', 'Travel Allowance (Fixed)', 3701),
       ('SUBSIST_LOCAL', 'Subsistence Allowance - Local Travel', 3704),
       ('SUBSIST_FOREIGN', 'Subsistence Allowance - Foreign Travel', 3715),
       ('OVERTIME', 'Overtime Payment', 3607),
       ('BONUS', 'Performance Bonus', 3713),
       ('COMMISSION', 'Commission', 3606),
       ('HOUSING', 'Housing Allowance', 3713),
       ('CELL', 'Cell Phone Allowance', 3802),
       ('TOOLS', 'Tool Allowance', 3710),
       ('UNIFORM', 'Uniform Allowance', 3709),
       ('ACTING', 'Acting Allowance', 3713),
       ('STANDBY', 'Standby Allowance', 3713),
       ('SHIFT', 'Shift Allowance', 3713),
       ('REMOTE', 'Remote Work Allowance', 3713),
       ('RELOCATION', 'Relocation Allowance', 3713),
       ('SCARCE_SKILL', 'Scarce Skill Allowance', 3713),
       ('DANGER', 'Danger Allowance', 3713),
       ('REIMBURSEMENT', 'Reimbursement (Non-Taxable)', 3702);
-- Example insert values
INSERT INTO sars.benefit (tax_year, code, name, irp5_code, taxable, contribution_employee, contribution_employer, cap, description)
VALUES
    ('2026', 'MEDICAL', 'Medical Aid Contributions', '26', FALSE, NULL, NULL, NULL, 'Medical aid contributions are partially tax-deductible per SARS rules'),
    ('2026', 'RETFUND', 'Employer Retirement Fund Contributions', '25', FALSE, NULL, NULL, NULL, 'Retirement contributions by employer, not taxable in employee hands'),
    ('2026', 'CARALLOW', 'Car Allowance', '14', TRUE, NULL, NULL, NULL, 'Taxable car allowance per SARS rules'),
    ('2026', 'HOUSALLOW', 'Housing Allowance', '17', TRUE, NULL, NULL, NULL, 'Taxable housing allowance for employees');

INSERT INTO sars.allowance_tax_rate
(allowance_type_id, tax_year, taxable_portion_percentage, effective_from, effective_to, active)
VALUES ((SELECT id FROM sars.allowance_type WHERE allowance_code = 'TRAVEL'), '2026', 80.00, '2025-03-01', NULL, true),
       ((SELECT id FROM sars.allowance_type WHERE allowance_code = 'SUBSIST_LOCAL'), '2026', 0.00, '2025-03-01', NULL,
        true),
       ((SELECT id FROM sars.allowance_type WHERE allowance_code = 'SUBSIST_FOREIGN'), '2026', 0.00, '2025-03-01', NULL,
        true),
       ((SELECT id FROM sars.allowance_type WHERE allowance_code = 'REIMBURSEMENT'), '2026', 0.00, '2025-03-01', NULL,
        true);

INSERT INTO sars.allowance_tax_rate
(allowance_type_id, tax_year, taxable_portion_percentage, effective_from, effective_to, active)
SELECT id, '2026', 100.00, '2025-03-01', NULL, true
FROM sars.allowance_type
WHERE allowance_code NOT IN ('TRAVEL', 'SUBSIST_LOCAL', 'SUBSIST_FOREIGN', 'REIMBURSEMENT');

INSERT INTO sars.medical_scheme_tax_credit_rate
(tax_year, credit_type_code, monthly_amount, effective_from, effective_to)
VALUES (2026, 'PRIMARY_MEMBER'::sars.medical_scheme_tax_credit_enum, 364.00, '2025-03-12', NULL);
INSERT INTO sars.medical_scheme_tax_credit_rate
(tax_year, credit_type_code, monthly_amount, effective_from, effective_to)
VALUES (2026, 'PRIMARY_AND_FIRST_DEPENDANT'::sars.medical_scheme_tax_credit_enum, 728.00, '2025-03-12', NULL);
INSERT INTO sars.medical_scheme_tax_credit_rate
(tax_year, credit_type_code, monthly_amount, effective_from, effective_to)
VALUES (2026, 'ADDITIONAL_DEPENDANT'::sars.medical_scheme_tax_credit_enum, 246.00, '2025-03-12', NULL);

INSERT INTO sars.pension_fund
    (tax_year, annual_remuneration_percent, maximum_amount)
VALUES ('2026', 27.50, 350000.00);

INSERT INTO sars.tax_rebate
    ("year", minimum_age, maximum_age, rebate_amount, rebate_description)
VALUES ('2026', 0, 65, 17235.00, 'PRIMARY');
INSERT INTO sars.tax_rebate
    ("year", minimum_age, maximum_age, rebate_amount, rebate_description)
VALUES ('2026', 65, 75, 9444.00, 'SECONDARY');
INSERT INTO sars.tax_rebate
    ("year", minimum_age, maximum_age, rebate_amount, rebate_description)
VALUES ('2026', 75, 999, 3145.00, 'TERTIARY');

INSERT INTO sars.uif
(effective_from, effective_to, employee_rate, employer_rate, monthly_earnings_cap, annual_earnings_cap, active)
VALUES ('2025-03-01', '9999-12-31', 0.01, 0.0100, 17712.00, 212544.00, true);

INSERT INTO sars.income_tax
("year", minimum_income, maximum_income, sliding_scale, minimum_taxable_amount, tax_percentage)
VALUES ('2026', 237101.00, 370500.00, 42678.00, 237100.00, 26);
INSERT INTO sars.income_tax
("year", minimum_income, maximum_income, sliding_scale, minimum_taxable_amount, tax_percentage)
VALUES ('2026', 370501.00, 512800.00, 77362.00, 370500.00, 31);
INSERT INTO sars.income_tax
("year", minimum_income, maximum_income, sliding_scale, minimum_taxable_amount, tax_percentage)
VALUES ('2026', 512801.00, 673000.00, 121475.00, 512800.00, 36);
INSERT INTO sars.income_tax
("year", minimum_income, maximum_income, sliding_scale, minimum_taxable_amount, tax_percentage)
VALUES ('2026', 673001.00, 857900.00, 179147.00, 673000.00, 39);
INSERT INTO sars.income_tax
("year", minimum_income, maximum_income, sliding_scale, minimum_taxable_amount, tax_percentage)
VALUES ('2026', 857901.00, 1817000.00, 251258.00, 857900.00, 41);
INSERT INTO sars.income_tax
("year", minimum_income, maximum_income, sliding_scale, minimum_taxable_amount, tax_percentage)
VALUES ('2026', 1817001.00, NULL, 644489.00, 1817000.00, 45);
INSERT INTO sars.income_tax
("year", minimum_income, maximum_income, sliding_scale, minimum_taxable_amount, tax_percentage)
VALUES ('2026', 1.00, 237100.00, 0.00, 0.00, 18);

INSERT INTO sars.tax_threshold
    ("year", minimum_age, maximum_age, threshold_amount)
VALUES ('2026', 0, 65, 95750.00),
       ('2026', 65, 75, 148217.00),
       ('2026', 75, 999, 165689.00);
