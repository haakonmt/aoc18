package no.quist.aoc18

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.quist.aoc18.days.Day1
import no.quist.aoc18.days.Day2
import java.time.LocalDate

val days = listOf(Day1, Day2)

fun main() {
    runCurrentDay()
}

@Suppress("UNUSED")
private fun runAllDays() = runBlocking {
    days.map { GlobalScope.launch { it.solveAndPrint() } }.forEach { it.join() }
}

@Suppress("UNUSED")
private fun runCurrentDay() {
    days[LocalDate.now().dayOfMonth - 1].solveAndPrint()
}
