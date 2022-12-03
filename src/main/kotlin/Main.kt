import solutions.Day01
import solutions.Day02
import solutions.Day03

fun main(args: Array<String>) {
    println("============================================================================================")
    println("************************************  DAY 01!  *********************************************")
    println("** Day01 Practice Test Case **")
    Day01().practice()
    println("** Day01 Real Test Case **")
    val day1 = Day01()
    println("Part 1 problem is https://adventofcode.com/2022/day/1")
    println("Part 1 solution is ${day1.part01()}")
    println("Part 2 problem is https://adventofcode.com/2022/day/1#part2")
    println("Part 2 solution is ${day1.part02()}")
    println("********************************************************************************************")

    println("************************************  DAY 02!  *********************************************")
    println("** Day02 Practice Test Case **")
    Day02().practice()
    println("** Day02 Real Test Case **")
    val day2 = Day02()
    println("Part 1 problem is https://adventofcode.com/2022/day/2")
    println("Part 1 solution is ${day2.part01()}")
    println("Part 2 problem is https://adventofcode.com/2022/day/2#part2")
    println("Part 2 solution is ${day2.part02()}")
    println("********************************************************************************************")

    println("************************************  DAY 03!  *********************************************")
    println("** Day03 Practice Test Case **")
    Day03().practice()
    println("** Day03 Real Test Case **")
    val day3 = Day03()
    println("Part 1 problem is https://adventofcode.com/2022/day/3")
    println("Part 1 solution is ${day3.part01()}")
    println("********************************************************************************************")
}