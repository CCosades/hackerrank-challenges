CREATE TABLE Company(
    company_code VARCHAR(5) NOT NULL PRIMARY KEY,
    founder VARCHAR(20)
);

CREATE TABLE Lead_Manager(
    lead_manager_code VARCHAR(5) NOT NULL PRIMARY KEY,
    company_code VARCHAR(5) FOREIGN KEY REFERENCES Company(company_code)
);

CREATE TABLE Senior_Manager(
    senior_manager_code VARCHAR(5) NOT NULL PRIMARY KEY,
    lead_manager_code VARCHAR(5) FOREIGN KEY REFERENCES Lead_Manager(lead_manager_code),
    company_code VARCHAR(5) FOREIGN KEY REFERENCES Company(company_code)
);

CREATE TABLE Manager(
    manager_code VARCHAR(5) NOT NULL PRIMARY KEY,
    senior_manager_code VARCHAR(5) FOREIGN KEY REFERENCES Senior_Manager(senior_manager_code),
    lead_manager_code VARCHAR(5) FOREIGN KEY REFERENCES Lead_Manager(lead_manager_code),
    company_code VARCHAR(5) FOREIGN KEY REFERENCES Company(company_code)
);

CREATE TABLE Employee(
    employee_code VARCHAR(5) NOT NULL PRIMARY KEY,
    manager_code VARCHAR(5) FOREIGN KEY REFERENCES Manager(manager_code),
    senior_manager_code VARCHAR(5) FOREIGN KEY REFERENCES Senior_Manager(senior_manager_code),
    lead_manager_code VARCHAR(5) FOREIGN KEY REFERENCES Lead_Manager(lead_manager_code),
    company_code VARCHAR(5) FOREIGN KEY REFERENCES Company(company_code)
);

INSERT INTO Company VALUES ('C1', 'Monika'), ('C2', 'Samantha');

INSERT INTO Lead_Manager VALUES ('LM1', 'C1'), ('LM2', 'C2');

INSERT INTO Senior_Manager VALUES ('SM1', 'LM1', 'C1'), ('SM2', 'LM1', 'C1'), ('SM3', 'LM2', 'C2');

INSERT INTO Manager VALUES ('M1', 'SM1', 'LM1', 'C1'), ('M2', 'SM3', 'LM2', 'C2'), ('M3', 'SM3', 'LM2', 'C2');

INSERT INTO Employee VALUES
    ('E1', 'M1', 'SM1', 'LM1', 'C1'),
    ('E2', 'M1', 'SM1', 'LM1', 'C1'),
    ('E3', 'M2', 'SM3', 'LM2', 'C2'),
    ('E4', 'M3', 'SM3', 'LM2', 'C2');