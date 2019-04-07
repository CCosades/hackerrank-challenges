SELECT
    c.company_code,
    c.founder,
    (SELECT count(distinct lead_manager_code) FROM Lead_Manager WHERE company_code=c.company_code),
    (SELECT count(distinct senior_manager_code) FROM Senior_Manager WHERE company_code=c.company_code),
    (SELECT count(distinct manager_code) FROM Manager WHERE company_code=c.company_code),
    (SELECT count(distinct employee_code) FROM Employee WHERE company_code=c.company_code)
FROM Company AS c
ORDER BY company_code;