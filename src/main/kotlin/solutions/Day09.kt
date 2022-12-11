package solutions

import utils.readInput


class Day09 {
    data class Point(var x: Int, var y: Int)
    private var input = readInput("day09/Day09")

    fun practice() {
        input = readInput("day09/Practice")

        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        return solver(2)
    }

    fun part02(): Int {
        return solver(10)
    }

    private fun solver(size: Int): Int {
        val visited = mutableSetOf<Point>()
        val points = (1..size).map { Point(0, 0) }
        val head = points.last()

        input.forEach {
            val (direction, amount) = it.split(" ")
            repeat(amount.toInt()) {
                when (direction) {
                    "L" -> {
                        head.x = head.x - 1
                    }

                    "U" -> {
                        head.y = head.y - 1
                    }

                    "R" -> {
                        head.x = head.x + 1
                    }

                    "D" -> {
                        head.y = head.y + 1
                    }
                }

                points.zipWithNext().reversed().forEach { (l, r) ->
                    if (!isTouching(l, r)) {
                        val t = r.direction(l)
                        l.x += t.x
                        l.y += t.y
                    }
                }

                visited += points.first().copy()
            }
        }

        return visited.size
    }

    private fun isTouching(head: Point, tail: Point): Boolean {
        listOf(-1, 0, 1).forEach { x ->
            listOf(-1, 0, 1).forEach { y ->
                if (tail.x + x == head.x && tail.y + y == head.y) {
                    return true
                }
            }
        }
        return false
    }

    private fun Point.direction(point: Point): Point {
        return Point(sgn(this.x - point.x), sgn(this.y - point.y))
    }

    private fun sgn(n: Int) = if (n < 0) -1 else if (n > 0) 1 else 0
}