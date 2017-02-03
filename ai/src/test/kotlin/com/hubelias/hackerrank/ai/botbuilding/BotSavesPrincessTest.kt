package com.hubelias.hackerrank.ai.botbuilding

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BotSavesPrincessTest {
    @Test fun testSavePrincess() {
        assertTrue(BotSavesPrincess.botCommands(0, 0, 0, 0).isEmpty())

        testCommands(listOf("LEFT"), BotSavesPrincess.botCommands(1, 1, 1, 2))
        testCommands(listOf("RIGHT"), BotSavesPrincess.botCommands(1, 1, 1, 0))

        testCommands(listOf("UP"), BotSavesPrincess.botCommands(1, 1, 2, 1))
        testCommands(listOf("DOWN"), BotSavesPrincess.botCommands(1, 1, 0, 1))

        testCommands(listOf("UP", "RIGHT"), BotSavesPrincess.botCommands(0, 4, 1, 3))
    }

    private fun testCommands(expected: List<String>, actual: List<String>) {
        assertEquals(expected.size, actual.size)
        expected.zip(actual).forEachIndexed { i, pair -> assertEquals("command $i differs", pair.first, pair.second) }
    }
}