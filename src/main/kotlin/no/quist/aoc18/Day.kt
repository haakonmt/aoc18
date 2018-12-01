package no.quist.aoc18

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
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
        launch { solveTaskTimed(::task1) }
        launch { solveTaskTimed(::task2) }
        println(className)
    }

    private val taskCounter = AtomicInteger(1)

    private fun solveTaskTimed(taskRunner: (I) -> R) {
        val input = this.input // Load outside timer
        var result = ""
        val time = measureTimeMillis {
            result = try {
                taskRunner(input).toString()
            } catch (err: NotImplementedError) {
                "NOT IMPLEMENTED"
            }
        }

        println("""
            |   Task ${taskCounter.getAndIncrement()}
            |       Result: $result
            |       Time: ${time}ms
        """.trimMargin())
    }
}
