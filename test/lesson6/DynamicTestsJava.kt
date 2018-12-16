package lesson6

import org.junit.jupiter.api.Tag
import java.lang.IllegalArgumentException
import kotlin.test.Test

class DynamicTestsJava : AbstractDynamicTests() {

    @Test
    @Tag("Normal")
    fun testLongestCommonSubSequence() {
        longestCommonSubSequence { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("Hard")
    fun testLongestIncreasingSubSequence() {
        longestIncreasingSubSequence { JavaDynamicTasks.longestIncreasingSubSequence(it) }
        belowZero { JavaDynamicTasks.longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("Hard")
    fun testShortestPathOnField() {
        shortestPathOnField { JavaDynamicTasks.shortestPathOnField(it) }
        zeroesField { JavaDynamicTasks.shortestPathOnField(it) }
        decimalField { JavaDynamicTasks.shortestPathOnField(it) }
        bigNumbers { JavaDynamicTasks.shortestPathOnField(it) }
    }
}