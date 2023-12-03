import java.util.ArrayList

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
                    //println("$symbolFound $serialNumber")
                    j++
                } else {
                    j++
                    buildNumber = true
                }
                if (buildNumber) {
                    if (serialNumber != "" && symbolFound) {
                        //println(Integer.parseInt(serialNumber))
                        sum += Integer.parseInt(serialNumber)
                        //println(serialNumber)
                    }

                    //val number = Integer.parseInt(serialNumber)
                    //println(number)
                    serialNumber = ""
                    symbolFound = false
                }
            }
            i++
        }
        return sum
    }

    fun part2(input: List<String>): Int {


        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 72)
    //check(part2(testInput) == 42)

    val input = readInput("Day03")
    part1(input).println() //512794
    part2(input).println()
}


fun isSymbol(item: String): Boolean {
    val trimmedItem = item.trim()
    return !(trimmedItem == "" || trimmedItem[0].isDigit() || trimmedItem[0] == '.')
}
