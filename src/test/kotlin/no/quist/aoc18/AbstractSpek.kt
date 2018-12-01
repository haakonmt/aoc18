package no.quist.aoc18

import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

abstract class AbstractSpek<I, R>(vararg tests: Pair<(I) -> R, Map<I, R>>) : Spek({
    tests.forEachIndexed { groupIndex, (testRunner, tests) ->
        group("Task ${groupIndex + 1}") {
            tests.keys.forEachIndexed { testIndex, input ->
                test("Test ${testIndex + 1}") {
                    assertEquals(tests[input], testRunner(input))
                }
            }
        }
    }
})
