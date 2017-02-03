package com.hubelias.hackerrank.contests.weekofcode26

import org.junit.Assert
import org.junit.Test

class TwinsTest {
    @Test fun defaultTest() {
        Assert.assertEquals(3, Twins.solve(3, 13))
    }

    @Test fun nmDifferenceLessThanTwo() {
        Assert.assertEquals(0, Twins.solve(3, 3))
        Assert.assertEquals(0, Twins.solve(3, 4))
        Assert.assertEquals(0, Twins.solve(85, 85))
        Assert.assertEquals(0, Twins.solve(85, 86))
    }

    @Test fun smallNM() {
        Assert.assertEquals(0, Twins.solve(1, 1))
        Assert.assertEquals(0, Twins.solve(1, 2))
        Assert.assertEquals(0, Twins.solve(1, 3))
        Assert.assertEquals(0, Twins.solve(1, 4))
    }

    @Test fun edgeCases() {
        Assert.assertEquals(1, Twins.solve(3,5))
        Assert.assertEquals(0, Twins.solve(5,6))
        Assert.assertEquals(1, Twins.solve(5,7))
        Assert.assertEquals(3, Twins.solve(3,13))
    }
}