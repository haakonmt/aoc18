package no.quist.aoc18

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

abstract class Day<I, R : Any> {
    abstract fun createInput(): I
    abstract fun part1(input: I): R
    abstract fun part2(input: I): R

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

    fun solveAndPrint() = runBlocking {
        listOf(::part1, ::part2)
            .mapIndexed { i, it -> GlobalScope.async { solvePartTimed(i + 1, it) } }
            .forEach { println(it.await()) }
    }

    private fun solvePartTimed(partIndex: Int, solver: (I) -> R): PartResult {
        val input = this.input // Load outside timer
        var result: Any = ""
        val time = measureTimeMillis {
            result = try {
                solver(input)
            } catch (err: NotImplementedError) {
                "NOT IMPLEMENTED"
            }
        }

        return PartResult(className, partIndex, result, time)
    }
}
