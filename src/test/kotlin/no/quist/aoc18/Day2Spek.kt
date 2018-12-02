package no.quist.aoc18

import no.quist.aoc18.days.Day2

object Day2Spek : AbstractSpek<List<String>, Any>(
    Day2::part1 to mapOf(
        listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab") to 12
    ),
    Day2::part2 to mapOf(
        listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz") to "fgij"
    )
)
