package no.quist.aoc18.days

import no.quist.aoc18.Day

object Day2 : Day<List<String>, Any>() {
    override fun createInput(): List<String> = inputLines

    override fun part1(input: List<String>): Int {
        val (twos, threes) = input.fold(Pair(0, 0)) { (accTwos, accThrees), currentLine ->
            val charCount = currentLine.map { c -> currentLine.count { it == c } }

            Pair(
                accTwos + if (charCount.contains(2)) 1 else 0,
                accThrees + if (charCount.contains(3)) 1 else 0
            )
        }
        return twos * threes
    }

    override fun part2(input: List<String>): String {
        input.forEach { first ->
            input.forEach { second ->
                val (diffCount, commonChars) = first.foldIndexed(Pair(0, "")) { i, (accDiff, accCommon), c ->
                    if (c != second[i]) Pair(accDiff + 1, accCommon) // Increment diff counter
                    else Pair(accDiff, accCommon + c) // Add to common chars
                }
                if (diffCount == 1) {
                    return commonChars
                }
            }
        }
        return "NO RESULT"
    }
}
