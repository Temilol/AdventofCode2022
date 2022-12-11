package solutions

import utils.readInput
import kotlin.collections.ArrayList

class Day11 {
    data class Operation(var sign: String, var digit: Long)

    private var input = readInput("day11/Day11")
    private var monkeys = ArrayList<ArrayDeque<Long>>()
    private var operations = ArrayList<Operation>()
    private var mods = ArrayList<Int>()
    private var trueResponse = ArrayList<Int>()
    private var falseResponse = ArrayList<Int>()
    private var inspections = ArrayList<Long>()

    fun practice() {
        input = readInput("day11/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Long {
        preload()
        repeat(20) {
            var i = 0
            while (i < monkeys.size) {
                val items = monkeys[i]
                inspections[i] = inspections[i] + items.size
                while (items.isNotEmpty()) {
                    val item = items.removeFirst()
                    val worryLevel = calculate(item, operations[i]) / 3
                    if (worryLevel % mods[i] == 0.toLong()) {
                        monkeys[trueResponse[i]].add(worryLevel)
                    } else {
                        monkeys[falseResponse[i]].add(worryLevel)
                    }
                }
                i++
            }
        }
        val sortedInspection = inspections.sortedDescending()
        postload()
        return sortedInspection[0] * sortedInspection[1]
    }

    fun part02(): Long {
        preload()
        val lcm = lcm(mods, 0)
        repeat(10000) {
            var i = 0
            while (i < monkeys.size) {
                val items = monkeys[i]
                inspections[i] = inspections[i] + items.size
                while (items.isNotEmpty()) {
                    val item = items.removeFirst()
                    val worryLevel = calculate(item, operations[i]) % lcm
                    if (worryLevel % mods[i] == 0.toLong()) {
                        monkeys[trueResponse[i]].add(worryLevel)
                    } else {
                        monkeys[falseResponse[i]].add(worryLevel)
                    }
                }
                i++
            }
        }
        val sortedInspection = inspections.sortedDescending()
        postload()
        return sortedInspection[0] * sortedInspection[1]
    }

    private fun postload() {
        monkeys.clear()
        operations.clear()
        mods.clear()
        trueResponse.clear()
        falseResponse.clear()
        inspections.clear()
    }

    private fun preload() {
        var i = 0
        while (i < input.size) {
            if (input[i].split(" ")[0] == "Monkey") {
                inspections.add(0)
                var j = 1
                while (i + 1 < input.size && input[i + 1].isNotEmpty()) {
                    i++
                    when (j) {
                        1 -> {
                            input[i].split(": ")[1].split(", ").map { it.toLong() }.also {
                                monkeys.add(ArrayDeque<Long>().apply { addAll(it) })
                            }
                        }

                        2 -> {
                            input[i].split("= old ")[1].split(" ").also {
                                val second = if (it[1] == "old") {
                                    -1
                                } else {
                                    it[1].toLong()
                                }
                                operations.add(Operation(it[0], second))
                            }
                        }

                        3 -> {
                            input[i].split("by ")[1].also {
                                mods.add(it.toInt())
                            }
                        }

                        4 -> {
                            input[i].split("monkey ")[1].also {
                                trueResponse.add(it.toInt())
                            }
                        }

                        5 -> {
                            input[i].split("monkey ")[1].also {
                                falseResponse.add(it.toInt())
                            }
                        }
                    }
                    j++
                }
            }
            i++
        }
    }

    private fun calculate(item: Long, operation: Operation): Long {
        val digit = if (operation.digit < 0) {
            item
        } else {
            operation.digit
        }
        return when (operation.sign) {
            "*" -> (item * digit)
            "+" -> item + digit
            else -> item
        }
    }

    private fun lcm(nums: List<Int>, index: Int): Int {
        if (index == nums.size - 1)
            return nums[index];
        val a = nums[index];
        val b = lcm(nums, index + 1);
        return (a * b) / gcd(a, b);
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) {
            a
        } else {
            gcd(b, a % b)
        }
    }

}