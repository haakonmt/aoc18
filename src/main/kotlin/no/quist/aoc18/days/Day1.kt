package no.quist.aoc18.days

import no.quist.aoc18.Day

object Day1 : Day<List<Int>, Int>() {
    override fun createInput(): List<Int> = inputLines.map { it.toInt() }

    override fun part1(input: List<Int>) = input.sum()

    override fun part2(input: List<Int>) = calculateTask2Result(input)

    private tailrec fun calculateTask2Result(input: List<Int>, startFreq: Int = 0, reachedFreqs: MutableSet<Int> = mutableSetOf(0)): Int {
        var frequency = startFreq
        return input.find {
            frequency += it
            !reachedFreqs.add(frequency)
        }?.let { frequency } ?: calculateTask2Result(input, frequency, reachedFreqs)
    }
}
