package solutions

import utils.readInput
import java.util.ArrayDeque
import java.util.ArrayList

class Day06 {
    private var input = readInput("day06/Day06")

    fun practice() {
        input = readInput("day06/Practice")
        println("Part 1 Practice Solution")
        part01()
        println("Part 2 Practice Solution")
        part02()
    }

    fun part01() {
        for (packet in input) {
            var i = 1
            for (marker in packet) {
                if (i >= 4) {
                    if (packet.substring(i - 4, i).allUnique()) {
                        println(i)
                        break
                    }
                }
                i++
            }
        }
    }

    fun part02() {
        for (packet in input) {
            var i = 1
            for (marker in packet) {
                if (i >= 14) {
                    if (packet.substring(i - 14, i).allUnique()) {
                        println(i)
                        break
                    }
                }
                i++
            }
        }
    }

    fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)

}