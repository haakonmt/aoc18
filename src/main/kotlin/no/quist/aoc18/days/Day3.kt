package no.quist.aoc18.days

import no.quist.aoc18.Day

object Day3 : Day<List<Rectangle>, Any>() {
    override fun createInput(): List<Rectangle> = inputLines.map { line ->
        val (id, x, y, width, height) = line groupBy Regex("""#(\d*) @ (\d*),(\d*): (\d*)x(\d*)""")
        Rectangle(id, x.toInt(), y.toInt(), width.toInt(), height.toInt())
    }

    override fun part1(input: List<Rectangle>): Int = input
        .foldToMap(0) { _, i -> i + 1 }
        .values
        .count { it >= 2 }

    override fun part2(input: List<Rectangle>): String {
        val covered = input.foldToMap(mutableListOf<String>()) { (id), list ->
            (list + id).toMutableList()
        }

        val overlapping = covered.values.filter { it.size >= 2 }.flatten()
        return input.find { (id) -> !overlapping.contains(id) }!!.id
    }

    private fun <T> List<Rectangle>.foldToMap(defaultValue: T, addValue: (Rectangle, T) -> T) : MutableMap<String, T> =
        fold(mutableMapOf()) { acc, r ->
            (r.x..(r.x + r.width - 1)).forEach { x ->
                (r.y..(r.y + r.height - 1)).forEach { y ->
                    acc["$x,$y"] = addValue(r, (acc["$x,$y"] ?: defaultValue))
                }
            }
            acc
        }
}

data class Rectangle(
    val id: String,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int
)
