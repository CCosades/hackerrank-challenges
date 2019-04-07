package com.hubelias.hackerrank.sql;

import java.util.Arrays;
import java.util.List;

public class NewCompanies extends AbstractSQLTestCase {

    @Override
    protected List<String> getExpectedResults() {
        return Arrays.asList(
                "C1 Monika 1 2 1 2",
                "C2 Samantha 1 1 2 2"
        );
    }
}
