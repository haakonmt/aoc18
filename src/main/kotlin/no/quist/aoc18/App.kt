package no.quist.aoc18

import no.quist.aoc18.days.Day1
import no.quist.aoc18.days.Day2

val days = listOf(Day1, Day2)

fun main() {
    days.forEach(Day<*, *>::solve)
}
