const val RED = 12
const val GREEN = 13
const val BLUE = 14

fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        for (i in input.indices) {
            var limitCrossed = false
            val sanitizedString = input[i].replace("Game ${i + 1}: ", "")
            var games = sanitizedString.split(";")
            games.forEach { it ->
                val subsets = it.split(",")
                subsets.forEach { i ->
                    val config = i.trim().split(" ")
                    when (config[1].lowercase()) {
                        "red" ->
                            if (config[0].toInt() > RED) limitCrossed = true

                        "blue" ->
                            if (config[0].toInt() > BLUE) limitCrossed = true

                        "green" ->
                            if (config[0].toInt() > GREEN) limitCrossed = true
                    }
                }
            }
            if (!limitCrossed) total += i + 1
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (i in input.indices) {
            var redMax = 1
            var blueMax = 1
            var greenMax = 1
            val sanitizedString = input[i].replace("Game ${i + 1}: ", "")
            var games = sanitizedString.split(";")
            games.forEach { it ->
                val subsets = it.split(",")
                subsets.forEach { i ->
                    val config = i.trim().split(" ")
                    when (config[1].lowercase()) {
                        "red" ->
                            if (config[0].toInt() > redMax) redMax = config[0].toInt()

                        "blue" ->
                            if (config[0].toInt() > blueMax) blueMax = config[0].toInt()

                        "green" ->
                            if (config[0].toInt() > greenMax) greenMax = config[0].toInt()
                    }
                }
            }
            total += redMax * blueMax * greenMax
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    //check(part1(testInput) == 72)
    //check(part2(testInput) == 42)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

