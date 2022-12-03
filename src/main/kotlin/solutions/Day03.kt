package solutions

import utils.readInput

class Day03 {
    private var input = readInput("day03/Day03")
    private var totalPriority = 0

    fun practice() {
        input = readInput("day03/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        totalPriority = 0
        for (rucksack in input) {
            val rucksackMid = rucksack.length / 2
            val firstRucksack = rucksack.substring(0, rucksackMid)
            val secondRucksack = rucksack.substring(rucksackMid)
            val common = intercest(firstRucksack, secondRucksack)
            val priority = getPriority(common)
            totalPriority += priority
        }
        return totalPriority
    }

    fun part02(): Int {
        totalPriority = 0
        var counter = 0
        val rucksackList = mutableListOf<String>()
        for (rucksack in input) {
            counter++
            if (counter <= 3) {
                rucksackList.add(rucksack)
                continue
            }
            var common = intercestString(rucksackList[0], rucksackList[1])
            common = intercestString(common, rucksackList[2])
            val priority = getPriority(common.single())
            totalPriority += priority
            rucksackList.clear()
            rucksackList.add(rucksack)
            counter = 1
        }

        if (rucksackList.isNotEmpty()) {
            var common = intercestString(rucksackList[0], rucksackList[1])
            common = intercestString(common, rucksackList[2])
            val priority = getPriority(common.single())
            totalPriority += priority
        }

        return totalPriority
    }

    private fun intercest(str1: String, str2: String): Char {
        val set1 = mutableSetOf<Char>()
        val set2 = mutableSetOf<Char>();
        for (i in str1) {
            set1.add(i)
        }
        for (i in str2) {
            set2.add(i)
        }
        set1.retainAll(set2)
        return set1.first()
    }

    private fun intercestString(str1: String, str2: String): String {
        val set1 = mutableSetOf<Char>()
        val set2 = mutableSetOf<Char>();
        for (i in str1) {
            set1.add(i)
        }
        for (i in str2) {
            set2.add(i)
        }
        set1.retainAll(set2)
        return set1.joinToString("")
    }

    private fun getPriority(char: Char): Int {
        return if (char.isLowerCase()) {
            (char - 'a') + 1
        } else {
            (char - 'A') + 27
        }
    }
}