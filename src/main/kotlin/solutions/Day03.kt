package solutions

import utils.readInput

class Day03 {
    private var input = readInput("day03/Day03")
    private var totalPriority = 0

    fun practice() {
        input = readInput("day03/Practice")
        println("Part 1 is ${part01()}")
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

    private fun getPriority(char: Char): Int {
        return if (char.isLowerCase()) {
            (char - 'a') + 1
        } else {
            (char - 'A') + 27
        }
    }
}