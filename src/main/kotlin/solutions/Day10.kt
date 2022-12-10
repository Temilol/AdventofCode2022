package solutions

import utils.readInput
import kotlin.collections.ArrayList
import kotlin.math.sign

class Day10 {
    private var input = readInput("day10/Day10")
    private val set = HashSet<Int>().apply {
        add(20)
        add(60)
        add(100)
        add(140)
        add(180)
        add(220)
    }

    fun practice() {
        input = readInput("day10/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        var i = 0
        var signalStrength = 1
        var totalStrength = 0
        for (action in input) {
            val signal = action.split(" ")
            when (signal[0]) {
                "noop" -> {
                    i++
                    if (set.contains(i)) {
                        totalStrength += (i * signalStrength)
                    }
                }

                "addx" -> {
                    var j = 0
                    while (j < 2) {
                        i++
                        if (set.contains(i)) {
                            totalStrength += (i * signalStrength)
                        }
                        j++
                    }
                    signalStrength += signal[1].toInt()
                }
            }
        }
        return totalStrength
    }

    fun part02() {
        var i = 0
        var location = 1
        for (action in input) {
            val signal = action.split(" ")
            when (signal[0]) {
                "noop" -> {
                    i++
                    if (i >= location && i <= location + 2) {
                        print("#")
                    } else {
                        print(".")
                    }
                    if (i % 40 == 0) {
                        println()
                        i = 0
                    }
                }

                "addx" -> {
                    var j = 0
                    while (j < 2) {
                        i++
                        if (i >= location && i <= location + 2) {
                            print("#")
                        } else {
                            print(".")
                        }
                        if (i % 40 == 0) {
                            println()
                            i = 0
                        }
                        j++
                    }
                    location += signal[1].toInt()
                }
            }
        }
    }


}