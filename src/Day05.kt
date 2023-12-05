import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): BigInteger {
        val seeds = ArrayList<BigInteger>()
        val locations = ArrayList<BigInteger>()
        input[0].replace("seeds: ", "").split(" ").forEach { seeds.add(it.trim().toBigInteger()) }
        val seedToSoilMap = getMap(input, input.indexOf("seed-to-soil map:"))
        val soilToFertilizerMap = getMap(input, input.indexOf("soil-to-fertilizer map:"))
        val fertilizerToWaterMap = getMap(input, input.indexOf("fertilizer-to-water map:"))
        val waterToLightMap = getMap(input, input.indexOf("water-to-light map:"))
        val lightToTemperatureMap = getMap(input, input.indexOf("light-to-temperature map:"))
        val tempToHumidityMap = getMap(input, input.indexOf("temperature-to-humidity map:"))
        val humidityToLocationMap = getMap(input, input.indexOf("humidity-to-location map:"))

        seeds.forEach {
            // it
            var value = getMappedValue(seedToSoilMap, it)
            value = getMappedValue(soilToFertilizerMap, value)
            value = getMappedValue(fertilizerToWaterMap, value)
            value = getMappedValue(waterToLightMap, value)
            value = getMappedValue(lightToTemperatureMap, value)
            value = getMappedValue(tempToHumidityMap, value)
            value = getMappedValue(humidityToLocationMap, value)
            locations.add(value)
        }

        return locations.min()
    }

    fun part2(input: List<String>): BigInteger {
        val seeds = ArrayList<BigInteger>()
        val locations = ArrayList<BigInteger>()
        input[0].replace("seeds: ", "").split(" ").forEach { seeds.add(it.trim().toBigInteger()) }
        val seedToSoilMap = getMap(input, input.indexOf("seed-to-soil map:"))
        val soilToFertilizerMap = getMap(input, input.indexOf("soil-to-fertilizer map:"))
        val fertilizerToWaterMap = getMap(input, input.indexOf("fertilizer-to-water map:"))
        val waterToLightMap = getMap(input, input.indexOf("water-to-light map:"))
        val lightToTemperatureMap = getMap(input, input.indexOf("light-to-temperature map:"))
        val tempToHumidityMap = getMap(input, input.indexOf("temperature-to-humidity map:"))
        val humidityToLocationMap = getMap(input, input.indexOf("humidity-to-location map:"))

        var mimimumvalue: BigInteger = BigInteger.valueOf(99999999999999)
        for (i in seeds.indices step 2) {
            var lower = seeds[i]
            val upper = seeds[i] + seeds[i + 1]
            println("$lower $upper")
            while (lower <= upper) {
                var value = getMappedValue(seedToSoilMap, lower)
                value = getMappedValue(soilToFertilizerMap, value)
                value = getMappedValue(fertilizerToWaterMap, value)
                value = getMappedValue(waterToLightMap, value)
                value = getMappedValue(lightToTemperatureMap, value)
                value = getMappedValue(tempToHumidityMap, value)
                value = getMappedValue(humidityToLocationMap, value)
                //locations.add(value)
                if (value < mimimumvalue) {
                    mimimumvalue = value
                }
                lower++
            }

        }

        return mimimumvalue
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    //check(part1(testInput) == 72)
    //check(part2(testInput) == 42)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

fun getMap(source: List<String>, index: Int): ArrayList<ArrayList<BigInteger>> {
    var loop = index + 1
    var maps = ArrayList<ArrayList<BigInteger>>()
    while (source[loop].isNotEmpty()) {
        maps.add(parseStringToArray(source[loop]))
        loop++
        if (loop == source.size) {
            break
        }
    }
    return maps
}

fun parseStringToArray(value: String): ArrayList<BigInteger> {
    val array = ArrayList<BigInteger>()
    value.split(" ").forEach { array.add(it.trim().toBigInteger()) }
    return array
}

fun getMappedValue(sources: ArrayList<ArrayList<BigInteger>>, value: BigInteger): BigInteger {
    sources.forEach {
        if (value >= it[1] && value <= it[1] + it[2]) {
            return value - it[1] + it[0]
        }
    }
    return value
}