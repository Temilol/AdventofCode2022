package solutions

import utils.readInput
import kotlin.collections.ArrayList

class Day08 {
    private var input = readInput("day08/Day08")
    private val grid = ArrayList<List<Int>>()

    fun practice() {
        input = readInput("day08/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        preload()
        var count = 0
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (visible(c, grid[r]) || visible(r, getColumn(grid, c))) {
                    count += 1
                }
            }
        }
        grid.clear()
        return count
    }

    fun part02(): Int {
        preload()
        var highestScenic = 0
        var scenicScore: Int
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                val rowS = calculateScenicScore(c, grid[r])
                val colS = calculateScenicScore(r, getColumn(grid, c))
                scenicScore = rowS * colS
                if (scenicScore > highestScenic) {
                    highestScenic = scenicScore
                }
            }
        }
        grid.clear()
        return highestScenic
    }

    private fun preload() {
        for (list in input) {
            val result = list.toCharArray().toList().map { it.digitToInt() }
            grid.add(result)
        }
    }

    private fun visible(index: Int, array: List<Int>): Boolean {
        var i = 0
        var foundL = true
        var foundR = true
        while (i < index) {
            if (array[i] >= array[index]) {
                foundL = false
            }
            i++;
        }

        i = array.size - 1
        while (i > index) {
            if (array[i] >= array[index]) {
                foundR = false
            }
            i--
        }
//        println(foundR)
//        println(foundL)

        return foundL || foundR
    }

    private fun getColumn(matrix: ArrayList<List<Int>>, col: Int): List<Int> {
        val result = ArrayList<Int>()
        for (list in matrix) {
            result.add(list[col])
        }

        return result
    }

    private fun calculateScenicScore(index: Int, array: List<Int>): Int {
        var scoreL = 0
        var scoreR = 0
        var i = index - 1
        while (i >= 0) {
            scoreL++
            if (array[i] >= array[index]) {
                break
            }
            i--
        }

        i = index + 1
        while (i < array.size) {
            scoreR++
            if (array[i] >= array[index]) {
                break
            }
            i++
        }
        return scoreL * scoreR
    }
}