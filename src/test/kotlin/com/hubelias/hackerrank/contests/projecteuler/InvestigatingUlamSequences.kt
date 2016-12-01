package com.hubelias.hackerrank.contests.projecteuler

import org.junit.Assert
import org.junit.Test

class AstonishingUlamSequenceTest {
    @Test
    fun sequenceShouldStartWithTwo() {
        Assert.assertEquals(2, AstonishingUlamSequence(2).getMember(1))
        Assert.assertEquals(2, AstonishingUlamSequence(5).getMember(1))
        Assert.assertEquals(2, AstonishingUlamSequence(10).getMember(1))
    }

    @Test
    fun secondElementShouldBeTwoNPlusOne() {
        Assert.assertEquals(5, AstonishingUlamSequence(2).getMember(2))
        Assert.assertEquals(11, AstonishingUlamSequence(5).getMember(2))
        Assert.assertEquals(21, AstonishingUlamSequence(10).getMember(2))
    }

    @Test
    fun testNEqualTwo() {
        val testedSeq = AstonishingUlamSequence(2)
        Assert.assertEquals(7, testedSeq.getMember(3))
        Assert.assertEquals(9, testedSeq.getMember(4))
        Assert.assertEquals(11, testedSeq.getMember(5))
        Assert.assertEquals(12, testedSeq.getMember(6))
        Assert.assertEquals(13, testedSeq.getMember(7))
        Assert.assertEquals(15, testedSeq.getMember(8))
        Assert.assertEquals(19, testedSeq.getMember(9))
        Assert.assertEquals(23, testedSeq.getMember(10))
        Assert.assertEquals(27, testedSeq.getMember(11))
        Assert.assertEquals(29, testedSeq.getMember(12))
        Assert.assertEquals(35, testedSeq.getMember(13))
        Assert.assertEquals(37, testedSeq.getMember(14))
        Assert.assertEquals(41, testedSeq.getMember(15))
        Assert.assertEquals(43, testedSeq.getMember(16))
    }
}
