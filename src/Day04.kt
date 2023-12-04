import java.sql.Array
import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var total = 0.0
        for (i in input.indices) {
            val sanitizedString = input[i].split(":")
            val cards = sanitizedString[1].trim().split("|")
            val winningCards = convertStringToArray(cards[0])
            val checkCards = convertStringToArray(cards[1])

            var power = 0
            var value = 0.0
            for (i in winningCards.indices) {
                val winningCard = winningCards[i]
                for (j in checkCards.indices) {
                    if (winningCard == checkCards[j]) {
                        value = 2.0.pow(power)
                        power++
                        break
                    }
                }

            }
            total += value
        }
        return total.toInt()
    }

    fun part2(input: List<String>): Int {
        var scratchCards = IntArray(input.size)

        for (i in input.indices) {
            val sanitizedString = input[i].split(":")
            val cards = sanitizedString[1].trim().split("|")
            val winningCards = convertStringToArray(cards[0])
            val checkCards = convertStringToArray(cards[1])
            var matches = 0
            for (k in winningCards.indices) {
                val winningCard = winningCards[k]
                for (j in checkCards.indices) {
                    if (winningCard == checkCards[j]) {
                        matches++
                        break
                    }
                }
            }
            scratchCards[i]++
            for (l in i + 1..i + matches) {
                if (scratchCards[i] > 0) {
                    var z = scratchCards[i]
                    while (z > 0) {
                        z--
                        scratchCards[l]++
                    }
                } else {
                    scratchCards[l]++
                }

            }
        }

        return scratchCards.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    //check(part1(testInput) == 72)
    //check(part2(testInput) == 42)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

fun convertStringToArray(array: String): List<Int> {
    val cardItems = ArrayList<Int>()
    array.split(" ").forEach {
        if (it.trim().isNotEmpty()) {
            cardItems.add(Integer.parseInt(it.trim()))
        }
    }
    return cardItems
}

