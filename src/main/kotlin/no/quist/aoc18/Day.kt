package no.quist.aoc18

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

abstract class Day<I, R> {
    abstract fun createInput(): I
    abstract fun task1(input: I): R
    abstract fun task2(input: I): R

    private val input: I by lazy { createInput() }

    private val className = javaClass.simpleName

    val inputLines: List<String> by lazy {
        try {
            javaClass.getResource("/$className.txt")
                .readText()
                .split("\r\n")
                .filter { it.isNotBlank() }
        } catch (ex: Exception) {
            println("Missing $className.txt in resources folder")
            emptyList<String>()
        }
    }

    fun solve() = runBlocking {
        val result1 = async { solveTaskTimed(::task1) }
        val result2 = async { solveTaskTimed(::task2) }
        println("""
            $className
                Task 1
                    Result: ${result1.await().second}
                    Time: ${result1.await().first}ms
                Task 2
                    Result: ${result2.await().second}
                    Time: ${result2.await().first}ms
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
