package no.quist.aoc18

import no.quist.aoc18.days.Day3
import no.quist.aoc18.days.Rectangle

val rectangles = listOf(
    Rectangle("1", 1, 3, 4, 4),
    Rectangle("2", 3, 1, 4, 4),
    Rectangle("3", 5, 5, 2, 2)
)

object Day3Spek : AbstractSpek<List<Rectangle>, Any>(
    Day3::part1 to mapOf(rectangles to 4),
    Day3::part2 to mapOf(rectangles to "3")
)
