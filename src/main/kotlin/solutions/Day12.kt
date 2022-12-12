package solutions

import utils.readInput
import kotlin.math.min

class Day12 {
    data class Coord(val row: Int, val col: Int)

    private var input = readInput("day12/Day12")
    private var grid = ArrayList<ArrayList<Char>>()
    private lateinit var starting: Coord
    private lateinit var ending: Coord

    fun practice() {
        input = readInput("day12/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        preload()
        val path = pathFinder(starting, ending)
        postLoad()
        return path
    }

    fun part02(): Int {
        preload()
        var shortest = Int.MAX_VALUE
        var i = 0
        while (i < grid.size) {
            var j = 0
            while (j < grid[i].size) {
                if (grid[i][j] == 'a') {
                    shortest = min(shortest, pathFinder(Coord(i, j), ending))
                }
                j++
            }
            i++
        }
        postLoad()
        return shortest
    }

    private fun pathFinder(start: Coord, end: Coord): Int {
        val cost = HashMap<Coord, Int>()
        val parent = HashMap<Coord, Coord>()
        val queue = ArrayDeque<Coord>()
        cost[start] = 0
        queue.add(start)
        while (queue.size > 0) {
            var curr = queue.removeFirst()
            if (curr == end) {
                val path = ArrayList<Coord>()
                var count = 0
                while (parent.containsKey(curr)) {
                    path.add(curr)
                    count++
                    curr = parent.getOrDefault(curr, Coord(-1, -1))
                }
                return count
            }
            for (currL in curr.directNeighbors()) {
                val row = currL.row
                val col = currL.col
                //skip if outside bounds or if it's more than one above current
                if (row < 0 || row >= grid.size || col < 0 || col >= grid[row].size || grid[row][col] > grid[curr.row][curr.col] + 1) {
                    continue
                }
                val temp = cost.getOrDefault(curr, -1).plus(1)
                if (temp < cost.getOrDefault(currL, Int.MAX_VALUE)) {
                    cost[currL] = temp
                    parent[currL] = curr
                    queue.add(currL)
                }
            }
        }
        return Int.MAX_VALUE
    }

    private fun preload() {
        var i = 0
        while (i < input.size) {
            var j = 0
            val array = ArrayList<Char>()
            while (j < input[i].length) {
                if (input[i][j] == 'S') {
                    starting = Coord(i, j)
                    array.add('a' - 1)
                } else if (input[i][j] == 'E') {
                    ending = Coord(i, j)
                    array.add('z' + 1)
                } else {
                    array.add(input[i][j])
                }
                j++
            }
            i++
            grid.add(array)
        }
    }

    private fun postLoad() {
        grid.clear()
    }

    private fun Coord.directNeighbors(): ArrayList<Coord> {
        val list = ArrayList<Coord>()
        for (yOff in -1..1) {
            for (xOff in -1..1) {
                //if not diagonal or self
                if ((xOff == 0) xor (yOff == 0)) {
                    list.add(Coord(this.row + xOff, this.col + yOff))
                }
            }
        }
        return list
    }
}