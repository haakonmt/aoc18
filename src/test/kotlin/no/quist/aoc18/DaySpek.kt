package no.quist.aoc18

import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

abstract class DaySpek<I, R>(
    day: Day<I, R>,
    task1Tests: Map<I, R>,
    task2Tests: Map<I, R>
) : Spek({
    group("Task 1") {
        task1Tests.keys.forEachIndexed { index, input ->
            test("Test ${index + 1}") {
                assertEquals(task1Tests[input], day.task1(input))
            }
        }
    }

    group("Task 2") {
        task2Tests.keys.forEachIndexed { index, input ->
            test("Test ${index + 1}") {
                assertEquals(task2Tests[input], day.task2(input))
            }
        }
    }
})
