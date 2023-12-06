fun main() {
    fun part1(input: List<String>): Int {
        val time = ArrayList<Int>()
        val distance = ArrayList<Int>()
        input[0].replace("Time:", "").split(" ").forEach {
            if (it.trim().isNotEmpty()) time.add(it.trim().toInt())
        }

        input[1].replace("Distance:", "").split(" ").forEach {
            if (it.trim().isNotEmpty()) distance.add(it.trim().toInt())
        }
        var total = 1
        for (i in time.indices) {
            var combinations = 0
            var j = 1
            val timeLimit = time[i]
            while (j <= timeLimit) {
                if ((time[i] - j) * j > distance[i]) combinations++
                j++
            }
            if (combinations > 0) total *= combinations
        }
        return total
    }

    fun part2(input: List<String>): Long {
        val time = input[0].replace("Time:", "").replace(" ", "").trim().toLong()
        val distance = input[1].replace("Distance:", "").replace(" ", "").trim().toLong()

        var combinations = 0L
        var j = 1
        while (j <= time) {
            if ((time - j) * j > distance) combinations++
            j++
        }

        return combinations
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    //check(part1(testInput) == 72)
    //check(part2(testInput) == 42)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}