package no.quist.aoc18

import kotlin.system.measureTimeMillis

object Utils {
    fun readDayInput(className: String) = try {
        javaClass.getResource("/$className.txt")
            .readText()
            .split("\r\n")
            .filter { it.isNotBlank() }
    } catch (ex: Exception) {
        println(ex.message)
        emptyList<String>()
    }


    fun <I, R> runDay(day: Day<I, R>) {
        val input = day.createInput()
        val (time1, result1) = runTask(input, day::task1)
        val (time2, result2) = runTask(input, day::task2)
        println("""
            ${day.javaClass.simpleName}
                Task 1
                    Result: $result1
                    Time: ${time1}ms
                Task 2
                    Result: $result2
                    Time: ${time2}ms
        """.trimIndent())
    }

    private fun <I, R> runTask(input: I, taskRunner: (I) -> R): Pair<Long, String> {
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

    @Suppress("UNUSED")
    fun <T> T.print() = apply {
        println(this)
    }
}

