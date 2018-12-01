package no.quist.aoc18

import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

typealias TestGroup<I, R> = Pair<(I) -> R, Map<I, R>>

abstract class AbstractSpek<I, R>(vararg groups: TestGroup<I, R>) : Spek({
    groups.forEachIndexed { gIndex, (testRunner, tests) ->
        group("Task ${gIndex + 1}") {
            tests.keys.forEachIndexed { tIndex, input ->
                test("Test ${tIndex + 1}") {
                    assertEquals(tests[input], testRunner(input))
                }
            }
        }
    }
})
