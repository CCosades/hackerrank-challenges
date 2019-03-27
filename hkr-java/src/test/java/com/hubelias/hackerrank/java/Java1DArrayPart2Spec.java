package com.hubelias.hackerrank.java;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Java1DArrayPart2Spec {

    @Data
    @RequiredArgsConstructor
    private static class TestCase {
        private final int leap;
        private final int[] game;
        private final boolean canWin;
        private String message;
    }

    @ParameterizedTest
    @MethodSource({
            "consecutiveOnes",
            "leapGreaterThanArraySize",
            "notSoObviousCases",
            "hackerRankExamples"
    })
    void testCanWin(TestCase testCase) {
        assertEquals(
                testCase.canWin,
                Java1DArrayPart2.canWin(0, testCase.leap, testCase.game),
                testCase.message
        );
    }

    //max number of consecutive "one" cells must be smaller than than leap value
    static Stream<TestCase> consecutiveOnes() {
        return Stream.of(
                new TestCase(0, new int[]{0, 0}, true),
                new TestCase(0, new int[]{0, 0, 0}, true),
                new TestCase(0, new int[]{0, 1, 0}, false),
                new TestCase(1, new int[]{0, 0, 0}, true),
                new TestCase(1, new int[]{0, 1, 0}, false),
                new TestCase(2, new int[]{0, 0, 0}, true),
                new TestCase(2, new int[]{0, 1, 0}, true),
                new TestCase(2, new int[]{0, 1, 1}, false),
                new TestCase(3, new int[]{0, 0, 0}, true),
                new TestCase(3, new int[]{0, 1, 0, 0}, true),
                new TestCase(3, new int[]{0, 1, 1, 0}, true),
                new TestCase(3, new int[]{0, 1, 1, 1}, false),
                new TestCase(3, new int[]{0, 1, 1, 1, 0}, false)
        );
    }

    static Stream<TestCase> leapGreaterThanArraySize() {
        return Stream.of(
                new TestCase(3, new int[]{0, 0}, true)
        );
    }

    static Stream<TestCase> notSoObviousCases() {
        return Stream.of(
                new TestCase(3, new int[]{0, 1, 1, 0, 1, 0, 1}, false),
                new TestCase(3, new int[]{0, 1, 1, 0, 0, 1, 1}, true),
                new TestCase(3, new int[]{0, 1, 1, 0, 0, 1, 0, 1}, true),
                new TestCase(3, new int[]{0, 1, 0, 0, 1, 0, 1}, true),
                new TestCase(4, new int[]{0, 0, 0, 1, 0, 1, 0, 0, 1, 1}, true)
        );
    }

    static Stream<TestCase> hackerRankExamples() {
        return Stream.of(
                new TestCase(3, new int[]{0, 0, 0, 0, 0}, true),
                new TestCase(5, new int[]{0, 0, 0, 1, 1, 1}, true),
                new TestCase(3, new int[]{0, 0, 1, 1, 1, 0}, false),
                new TestCase(1, new int[]{0, 1, 0}, false)
        );
    }
}
