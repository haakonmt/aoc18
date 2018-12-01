package no.quist.aoc18

import no.quist.aoc18.days.Day1

val days = listOf(Day1)

fun main() {
    days.forEach {
        Utils.runDay(it)
    }
}
