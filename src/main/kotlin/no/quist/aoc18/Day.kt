package no.quist.aoc18

import kotlin.system.measureTimeMillis

abstract class Day<I, R> {
    abstract fun createInput(): I
    abstract fun task1(input: I): R
    abstract fun task2(input: I): R

    private val input: I by lazy { createInput() }

    val inputLines: List<String> by lazy {
        try {
            javaClass.getResource("/${javaClass.simpleName}.txt")
                .readText()
                .split("\r\n")
                .filter { it.isNotBlank() }
        } catch (ex: Exception) {
            println("Missing ${javaClass.simpleName}.txt in resources folder")
            emptyList<String>()
        }
    }

    fun solve() {
        val (time1, result1) = solveTaskTimed(::task1)
        val (time2, result2) = solveTaskTimed(::task2)
        println("""
            ${javaClass.simpleName}
                Task 1
                    Result: $result1
                    Time: ${time1}ms
                Task 2
                    Result: $result2
                    Time: ${time2}ms
        """.trimIndent())
    }

    private fun solveTaskTimed(taskRunner: (I) -> R): Pair<Long, String> {
        val input = this.input // Load outside timer
        var result = ""
        val time = measureTimeMillis {
            result = try {
                taskRunner(input).toString()
            } catch (err: NotImplementedError) {
                "NOT IMPLEMENTED"
            }
        }

        return time to result
    }
}
