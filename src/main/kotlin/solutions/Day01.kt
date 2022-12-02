package solutions

import utils.readInput

class Day01 {
    private val caloriesInput = readInput("day01/Day01")
    private val caloriesSet = mutableSetOf<Int>()

    fun part01(): Int {
        var totalCalories = 0
        var highestCalories = 0
        for (calories in caloriesInput) {
            if (calories == "") {
                if (totalCalories > highestCalories) {
                    highestCalories = totalCalories
                }
                caloriesSet.add(totalCalories)
                totalCalories = 0
            } else {
                totalCalories += calories.toInt()
            }
        }

        return highestCalories
    }

    fun part02(): Int {
        val caloriesArray = caloriesSet.sortedDescending()
        return caloriesArray[0] + caloriesArray[1] + caloriesArray[2]
    }
}