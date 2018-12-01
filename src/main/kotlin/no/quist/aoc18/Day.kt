package no.quist.aoc18

abstract class Day<Input, Result> {
    abstract fun createInput(): Input
    abstract fun task1(input: Input): Result
    abstract fun task2(input: Input): Result

    val lines: List<String>
        get() = Utils.readDayInput(javaClass.simpleName)
}
