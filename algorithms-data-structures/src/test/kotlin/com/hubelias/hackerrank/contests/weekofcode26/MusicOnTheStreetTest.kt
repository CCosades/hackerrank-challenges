package com.hubelias.hackerrank.contests.weekofcode26

import org.junit.Assert
import org.junit.Test

class MusicStreetTest {
    @Test fun `First node should be beginning node at planned walk distance from first border`() {
        val firstNode = getNodes(borders = intArrayOf(3, 25, 40), walkDistance = 20, distanceOfMaxTime = 30).first()
        Assert.assertEquals(3 - 20, firstNode.position)
        Assert.assertEquals(NodeType.BEGINNING, firstNode.type)
    }

    private fun getNodes(borders: IntArray, walkDistance: Int = 1, distanceOfMinTime: Int = 1, distanceOfMaxTime: Int = 1)
            = MusicStreet(borders, walkDistance, distanceOfMinTime, distanceOfMaxTime).nodes


    @Test fun `If distance of max time is smaller than planned walk distance use it for finding first node position, we will call the smaller of these two distances max genre distance`() {
        val firstNode = getNodes(borders = intArrayOf(3, 25, 40), walkDistance = 20, distanceOfMaxTime = 8).first()
        Assert.assertEquals(3 - 8, firstNode.position)
        Assert.assertEquals(NodeType.BEGINNING, firstNode.type)
    }

    @Test fun `Last node should be ending node at max genre distance`() {
        val lastNode = getNodes(borders = intArrayOf(-100, 0, 232), walkDistance = 100, distanceOfMaxTime = 54).last()
        Assert.assertEquals("walkDistance > distanceOfMaxTime", 232 + 54, lastNode.position)
        Assert.assertEquals(NodeType.ENDING, lastNode.type)
        val lastNode2 = getNodes(borders = intArrayOf(-100, 0, 412), walkDistance = 24, distanceOfMaxTime = 100).last()
        Assert.assertEquals("walkDistance < distanceOfMaxTime", 412 + 24, lastNode2.position)
        Assert.assertEquals(NodeType.ENDING, lastNode2.type)
    }

    @Test fun `If distance between border is smaller than distance of min time, mark area as unavailable`() {
        val nodes = getNodes(borders = intArrayOf(0, 50, 51, 100), distanceOfMinTime = 2)
        Assert.assertTrue(nodes.any { it.position == 50 && it.type == NodeType.ENDING })
        Assert.assertTrue(nodes.any { it.position == 51 && it.type == NodeType.BEGINNING })
    }

    @Test fun `If distance between borders is greater than max genre distance treat the right border as it was first border and create beginning node appropriately`() {
        val nodes = getNodes(borders = intArrayOf(0, 1, 10, 11), walkDistance = 3, distanceOfMaxTime = 2)
        Assert.assertNotNull("walkDistance > distanceOfMaxTime", nodes.singleOrNull { it.type == NodeType.BEGINNING && it.position == 10 - 2 })
        Assert.assertTrue("not added node in inappropriate places", nodes.none { it.position == 1 - 2 })
        val nodes2 = getNodes(borders = intArrayOf(0, 1, 10, 11), walkDistance = 5, distanceOfMaxTime = 20)
        Assert.assertNotNull("walkDistance < distanceOfMaxTime", nodes2.singleOrNull { it.type == NodeType.BEGINNING && it.position == 10 - 5 })
    }

    @Test fun `If distance between borders is greater than max genre distance treat the left border as it was last border and create ending node appropriately`() {
        val nodes = getNodes(borders = intArrayOf(0, 1, 10, 11), walkDistance = 3, distanceOfMaxTime = 2)
        Assert.assertNotNull("walkDistance > distanceOfMaxTime", nodes.singleOrNull { it.type == NodeType.ENDING && it.position == 1 + 2 })
        Assert.assertTrue("not added node in inappropriate places", nodes.none { it.position == 10 + 2 })
        val nodes2 = getNodes(borders = intArrayOf(0, 1, 10, 11), walkDistance = 5, distanceOfMaxTime = 20)
        Assert.assertNotNull("walkDistance < distanceOfMaxTime", nodes2.singleOrNull { it.type == NodeType.ENDING && it.position == 1 + 5 })
    }

    @Test fun `If distance between borders is greater than max genre distance and added beginning is left to added ending mark the ending as skippable for the added beginning`() {
        val nodes = getNodes(borders = intArrayOf(0, 1, 10, 11), walkDistance = 4, distanceOfMaxTime = 4)
        Assert.assertTrue("not skip by default", nodes.none { it.skipOneEnding })

        val nodes2 = getNodes(borders = intArrayOf(0, 1, 10, 11), walkDistance = 6, distanceOfMaxTime = 6)
        val skippingNode = nodes2.singleOrNull { it.skipOneEnding }
        Assert.assertNotNull("skip if overlap", skippingNode)
        Assert.assertEquals("correct position", 10 - 6, skippingNode?.position)
    }
}