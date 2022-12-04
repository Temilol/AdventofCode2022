package solutions

import utils.readInput

class Day04 {
    private var input = readInput("day04/Day04")

    fun practice() {
        input = readInput("day04/Practice")
        println("Part 1 is ${part01()}")
    }

    fun part01(): Int {
        var overlapCounter = 0
        for (task in input) {
            val individual = task.split(",")
            val p0 = individual[0].split("-")
            val p1 = individual[1].split("-")
            val p0Start = p0[0].toInt()
            val p0End = p0[1].toInt()
            val p1Start = p1[0].toInt()
            val p1End = p1[1].toInt()

            if ((p0Start <= p1Start && p0End >= p1End) || (p1Start <= p0Start && p1End >= p0End)) {
                overlapCounter++;
            }
        }
        return overlapCounter
    }


}