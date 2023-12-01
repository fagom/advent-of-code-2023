fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {
            sum += parseStringForPart1(it)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach {
            sum += parseStringForPart2(it)
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 72)
    check(part2(testInput) == 42)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

val maps = mapOf(
    "zero" to 0,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun parseStringForPart1(doc: String): Int {
    var firstNum: Int = -1
    var secondNum: Int = -1
    for (i in doc.indices) {
        if (doc[i].isDigit()) {
            firstNum = doc[i].code - 48
            break
        }
    }

    for (i in doc.indices) {
        if (doc[doc.length - 1 - i].isDigit()) {
            secondNum = doc[doc.length - 1 - i].code - 48
            break
        }
    }
    return firstNum * 10 + secondNum
}

fun parseStringForPart2(doc: String): Int {
    var firstNum: Int = 0
    var index = 9999999
    var secondNum: Int = 0
    maps.forEach {
        if (doc.indexOf(it.key) != -1) {
            if (doc.lowercase().indexOf(it.key) <= index) {
                index = doc.indexOf(it.key)
                firstNum = it.value
            }
        }
    }

    for (i in doc.indices) {
        if (doc[i].isDigit() && i <= index) {
            firstNum = doc[i].code - 48
            break
        }
    }

    // reset index for deriving the 2nd number
    index = 0

    maps.forEach {
        if (doc.indexOf(it.key) != -1) {
            if (doc.lowercase().lastIndexOf(it.key) >= index) {
                index = doc.lastIndexOf(it.key)
                secondNum = it.value
            }
        }
    }

    for (i in doc.indices) {
        if (doc[doc.length - 1 - i].isDigit() && doc.length - 1 - i >= index) {
            secondNum = doc[doc.length - 1 - i].code - 48
            break
        }
    }

    return firstNum * 10 + secondNum
}

