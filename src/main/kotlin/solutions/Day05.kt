package solutions

import utils.readInput
import java.util.*

class Day05 {
    private var input = readInput("day05/Day05")
    private var stack = ArrayDeque<Char>()
    private val arrayStack: ArrayList<ArrayDeque<Char>> = ArrayList()

    private fun initPractice() {
        arrayStack.clear()
        stack = ArrayDeque(listOf('Z', 'N').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('M', 'C', 'D').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('P').reversed())
        stack.push('P')
        arrayStack.add(stack)
        stack = ArrayDeque<Char>()
    }

    private fun initProblem() {
        arrayStack.clear()
        stack = ArrayDeque(listOf('Q', 'F', 'M', 'R', 'L', 'W', 'C', 'V').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('D', 'Q', 'L').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('P', 'S', 'R', 'G', 'W', 'C', 'N', 'B').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('L', 'C', 'D', 'H', 'B', 'Q', 'G').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('V', 'G', 'L', 'F', 'Z', 'S').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('D', 'G', 'N', 'P').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('D', 'Z', 'P', 'V', 'F', 'C', 'W').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('C', 'P', 'D', 'M', 'S').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque(listOf('Z', 'N', 'W', 'T', 'V', 'M', 'P', 'C').reversed())
        arrayStack.add(stack)
        stack = ArrayDeque<Char>()
    }

    fun practice() {
        input = readInput("day05/Practice")
        println("Part 1 is ${part01(true)}")
        println("Part 2 is ${part02(true)}")
    }

    fun part01(practice: Boolean = false): String {
        if (practice) {
            initPractice()
        } else {
            initProblem()
        }
        for (crate in input) {
            val splits: List<String> = crate.split("move", "from", "to")
            var move = splits[1].trim().toInt()
            val from = splits[2].trim().toInt()
            val to = splits[3].trim().toInt()
            while (move > 0) {
                arrayStack[to - 1].push(arrayStack[from - 1].pop())
                move--
            }
        }
        var ans = ""
        for (stack in arrayStack) {
            ans += stack.pop()
        }
        return ans
    }

    fun part02(practice: Boolean = false): String {
        if (practice) {
            initPractice()
        } else {
            initProblem()
        }
        for (crate in input) {
            val splits: List<String> = crate.split("move", "from", "to")
            var move = splits[1].trim().toInt()
            val from = splits[2].trim().toInt()
            val to = splits[3].trim().toInt()
            val newStack = Stack<Char>()
            while (move > 0) {
                newStack.push(arrayStack[from - 1].pop())
                move--
            }
            while (newStack.isNotEmpty()) {
                arrayStack[to - 1].push(newStack.pop())
            }
        }
        var ans = ""
        for (stack in arrayStack) {
            ans += stack.pop()
        }
        return ans
    }
}