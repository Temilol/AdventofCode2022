package solutions

import utils.readInput
import java.util.*
import kotlin.math.abs

class Day07 {
    private var input = readInput("day07/Day07")
    private var directory = ArrayDeque<String>()
    private var mapping = HashMap<String, Int>()

    fun practice() {
        input = readInput("day07/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        preloadData()
        var total = 0
        for (map in mapping) {
            if (map.value <= 100000) {
                total += map.value
            }
        }
        directory.clear()
        mapping.clear()

        return total
    }

    fun part02(): Int {
        preloadData()
        val max = mapping.maxBy { it.value }
        val remain = abs(70000000 - max.value)
        val needed = abs(30000000 - remain)
        val result = mapping.toList().sortedBy { (_, value) -> value }.toMap()
        for (an in result) {
            if (an.value >= needed) {
                return an.value
            }
        }
        directory.clear()
        mapping.clear()

        return 0
    }


    private fun preloadData() {
        var localSum = 0
        var flag = true
        for (command in input) {
            val action = command.split(" ")
            if (action[0] == "$") {
                if (action[1] == "cd") {
                    if (action[2] == "..") {
                        localSum += mapping.getOrDefault(directory.toString(), 0)
                        mapping[directory.toString()] = localSum
                        directory.pop()
                        if (directory.isNotEmpty()) {
                            localSum += mapping.getOrDefault(directory.toString(), 0)
                            mapping[directory.toString()] = localSum
                        }
                        flag = true
                    } else if (flag) {
                        directory.push(action[2])
                        mapping[directory.toString()] = 0
                        flag = false
                    } else {
                        localSum += mapping.getOrDefault(directory.toString(), 0)
                        mapping[directory.toString()] = localSum
                        directory.push(action[2])
                    }
                    localSum = 0
                }
            } else if (action[0] != "dir") {
                localSum += action[0].toInt()
            }
        }
        while (directory.isNotEmpty()) {
            localSum += mapping.getOrDefault(directory.toString(), 0)
            mapping[directory.toString()] = localSum
            directory.pop()
        }
    }
}