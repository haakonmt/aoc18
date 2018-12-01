package no.quist.aoc18

import no.quist.aoc18.days.Day1

object Day1Spek : DaySpek<List<Int>, Int>(
    day = Day1,
    task1Tests = mapOf(
        listOf(1, 1, 1) to 3,
        listOf(1, 1, -2) to 0,
        listOf(-1, -2, -3) to -6
    ),
    task2Tests = mapOf(
        listOf(1, -1) to 0,
        listOf(3, 3, 4, -2, -4) to 10,
        listOf(-6, 3, 8, 5, -6) to 5,
        listOf(7, 7, -2, -7, -4) to 14
    )
)
