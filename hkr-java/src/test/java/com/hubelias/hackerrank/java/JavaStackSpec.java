package com.hubelias.hackerrank.java;

import lombok.Data;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class JavaStackSpec {

    @Data
    private static class Example {
        private final String string;
        private final boolean isBalanced;
    }

    @TestFactory
    Stream<DynamicTest> isBalanced() {
        return Stream.of(
                new Example("", true),
                new Example("{}()", true),
                new Example("({()})", true),
                new Example("{}(", false),
                new Example("[]", true),
                new Example("(A)", false)
        ).map(example -> dynamicTest(
                String.format(
                        "'%s' should %s balanced",
                        example.string,
                        example.isBalanced ? "be" : "NOT be"
                ), () -> assertEquals(
                        example.isBalanced,
                        JavaStack.isBalanced(example.string)
                )
        ));
    }
}
