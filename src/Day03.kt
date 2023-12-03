//TODO: Refactor this puzzle code for efficiency. Code smells !!!
fun main() {
    fun part1(input: List<String>): Int {
        var i = 0
        var sum = 0
        while (i < input.size) {
            val schematic = input[i].trim().split("")
            var j = 0
            var buildNumber = true
            var serialNumber: String = ""
            var symbolFound = false
            while (j < schematic.size) {
                //build serial numbers
                val coordinate = schematic[j].trim()
                // check for digits
                if (coordinate.isNotEmpty() && coordinate[0].isDigit()) {
                    if (!symbolFound) {
                        // check for left bound symbols
                        if (j > 0 && schematic[j - 1].isNotEmpty() && isSymbol(schematic[j - 1])) {
                            symbolFound = true
                        }

                        // check for right bound symbols
                        if (j < schematic.size - 1 && schematic[j + 1].isNotEmpty() && isSymbol(schematic[j + 1])) {
                            symbolFound = true
                        }

                        // check for 1st row
                        if (i == 0) {
                            val boundarySchematic = input[1].trim().split("")
                            if (j > 0 && (boundarySchematic[j + 1].isNotEmpty()
                                        && isSymbol(boundarySchematic[j + 1]) ||
                                        boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                            // check for right bound symbols
                            if (j < schematic.size - 1
                                && (isSymbol(boundarySchematic[j + 1])
                                        && boundarySchematic[j + 1].isNotEmpty()
                                        || isSymbol(boundarySchematic[j]) && boundarySchematic[j].isNotEmpty())
                            ) {
                                symbolFound = true
                            }

                            // check for lower bound symbols
                            if (j > 0 &&
                                (boundarySchematic[j - 1].isNotEmpty() && isSymbol(boundarySchematic[j - 1])
                                        || boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                            // check for right bound symbols
                            if (j < schematic.size - 1
                                && (boundarySchematic[j + 1].isNotEmpty() && isSymbol(boundarySchematic[j + 1]) ||
                                        boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                        }
                        // check for upper bound symbols
                        if (i > 0) {
                            val boundarySchematic = input[i - 1].trim().split("")
                            if (j > 0 && (boundarySchematic[j - 1].isNotEmpty()
                                        && isSymbol(boundarySchematic[j - 1]) ||
                                        boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                            // check for right bound symbols
                            if (j < schematic.size - 1
                                && (isSymbol(boundarySchematic[j + 1])
                                        && boundarySchematic[j + 1].isNotEmpty()
                                        || isSymbol(boundarySchematic[j]) && boundarySchematic[j].isNotEmpty())
                            ) {
                                symbolFound = true
                            }
                        }

                        // check for lower bound symbols
                        if (i > 0 && i < input.size - 1) {
                            val boundarySchematic = input[i + 1].trim().split("")
                            if (j > 0 &&
                                (boundarySchematic[j - 1].isNotEmpty() && isSymbol(boundarySchematic[j - 1])
                                        || boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                            // check for right bound symbols
                            if (j < schematic.size - 1
                                && (boundarySchematic[j + 1].isNotEmpty() && isSymbol(boundarySchematic[j + 1]) ||
                                        boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }
                        }

                        //last row
                        if (i == input.size - 1) {
                            val boundarySchematic = input[i - 1].trim().split("")
                            if (j > 0 &&
                                (boundarySchematic[j - 1].isNotEmpty() && isSymbol(boundarySchematic[j - 1])
                                        || boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                            // check for right bound symbols
                            if (j < schematic.size - 1
                                && (boundarySchematic[j + 1].isNotEmpty() && isSymbol(boundarySchematic[j + 1]) ||
                                        boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                            //check for upper bound symbols
                            if (j > 0 && (boundarySchematic[j - 1].isNotEmpty()
                                        && isSymbol(boundarySchematic[j - 1]) ||
                                        boundarySchematic[j].isNotEmpty() && isSymbol(boundarySchematic[j]))
                            ) {
                                symbolFound = true
                            }

                            // check for right bound symbols
                            if (j < schematic.size - 1
                                && (isSymbol(boundarySchematic[j + 1])
                                        && boundarySchematic[j + 1].isNotEmpty()
                                        || isSymbol(boundarySchematic[j]) && boundarySchematic[j].isNotEmpty())
                            ) {
                                symbolFound = true
                            }
                        }
                    }

                    serialNumber += coordinate[0]
                    buildNumber = false
                    j++
                } else {
                    j++
                    buildNumber = true
                }
                if (buildNumber) {
                    if (serialNumber != "" && symbolFound) {
                        sum += Integer.parseInt(serialNumber)
                    }
                    serialNumber = ""
                    symbolFound = false
                }
            }
            i++
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val gears = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        findCoordinates(input, { it == '*' }) { number, symbol ->
            gears[symbol] = gears[symbol]?.let { (n, m) -> n + 1 to m * number } ?: (1 to number)
        }
        return gears.values.asSequence().filter { it.first == 2 }.sumOf { it.second }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 72)
    //check(part2(testInput) == 42)

    val input = readInput("Day03")
    part1(input).println() //512794
    part2(input).println()
}

private infix fun IntRange.cartesian(other: IntRange): Sequence<Pair<Int, Int>> =
    asSequence().flatMap { f -> other.asSequence().map { s -> f to s } }

private fun findCoordinates(
    records: List<String>,
    adjacentCondition: (Char) -> Boolean,
    action: (Int, Pair<Int, Int>) -> Unit,
) {
    records.forEachIndexed { i, line ->
        var j = -1

        while (++j < line.length) {
            if (!line[j].isDigit()) continue

            val numberString = line.substring(j).takeWhile { it.isDigit() }

            val symbol = (i - 1..i + 1 cartesian j - 1..j + numberString.length)
                .find { (a, b) -> records.getOrNull(a)?.getOrNull(b)?.let(adjacentCondition) == true }
                ?: continue
            action(numberString.toInt(), symbol)

            j += numberString.length - 1
        }
    }
}

private fun inputBufferedReader() = System.`in`.bufferedReader()

private fun getFullInput(): String = inputBufferedReader().readText().trimEnd()

fun lines(): List<String> = mapLines { it }

fun <T> mapLines(mapper: (String) -> T): List<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun <T> mapBlocks(mapper: (List<String>) -> T): List<T> =
    getFullInput().splitToSequence("\n\n").map { mapper(it.lines()) }.toMutableList()

// Parsing utilities

fun String.toInts(vararg delimiters: String = arrayOf(" ", ",")): List<Int> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()

// Error handling utilities

fun <T> T?.unwrap(): T = this ?: expect()

fun expect(): Nothing = error("Invalid input / Unreachable")

fun isSymbol(item: String): Boolean {
    val trimmedItem = item.trim()
    return !(trimmedItem == "" || trimmedItem[0].isDigit() || trimmedItem[0] == '.')
}
