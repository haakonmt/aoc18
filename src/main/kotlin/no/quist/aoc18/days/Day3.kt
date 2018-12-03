package no.quist.aoc18.days

import no.quist.aoc18.Day

object Day3 : Day<List<Rectangle>, Any>() {
    override fun createInput(): List<Rectangle> = inputLines.map { line ->
        val (rawId, parts) = line.split(" @ ")
        val id = rawId.drop(1)
        val (coordinates, size) = parts.split(": ")
        val (x, y) = coordinates.split(",").map { it.toInt() }
        val (width, height) = size.split("x").map { it.toInt() }
        Rectangle(id, x, y, width, height)
    }

    override fun part1(input: List<Rectangle>): Int {
        val covered = foldRectangles(mutableMapOf(), 0, input) { _, i ->
            i + 1
        }
        return covered.values.count { it >= 2 }
    }

    override fun part2(input: List<Rectangle>): String {
        val covered = foldRectangles(mutableMapOf(), mutableListOf<String>(), input) { (id), list ->
            (list + id).toMutableList()
        }
        return input.find { r ->
            !covered.values.filter { it.size >= 2 }.flatten().contains(r.id)
        }!!.id
    }

    private fun <T> foldRectangles(initialValue: MutableMap<String, T>, defaultValue: T, input: List<Rectangle>, addValue: (Rectangle, T) -> T) : MutableMap<String, T> =
        input.fold(initialValue) { acc, r ->
            (r.x..(r.x + r.width - 1)).forEach { x ->
                (r.y..(r.y + r.height - 1)).forEach { y ->
                    val value = (acc["$x,$y"] ?: defaultValue)
                    acc["$x,$y"] = addValue(r, value)
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
