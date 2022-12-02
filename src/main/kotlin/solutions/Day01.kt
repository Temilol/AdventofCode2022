package solutions

import utils.readInput

class Day01 {
    private val caloriesList = readInput("day01/Day01")
    private val caloriesSet = mutableSetOf<Int>()
    private var totalCalories = 0
    private var highestCalories = 0

    fun part01(): Int {
        for (calories in caloriesList) {
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

}