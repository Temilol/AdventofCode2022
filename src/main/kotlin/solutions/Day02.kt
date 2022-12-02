package solutions

import utils.readInput

class Day02 {
    private var input = readInput("day02/Day02")
    private var delimiter = " "
    private var totalScore = 0;
    private val rpsMapping = HashMap<String, Char>().apply {
        putIfAbsent("A", 'R')
        putIfAbsent("B", 'P')
        putIfAbsent("C", 'S')
        putIfAbsent("X", 'R')
        putIfAbsent("Y", 'P')
        putIfAbsent("Z", 'S')
    }
    private val outcomeScoreMapping = HashMap<Char, Int>().apply {
        putIfAbsent('W', 6)
        putIfAbsent('D', 3)
        putIfAbsent('L', 0)
    }
    private val shapeScoreMapping = HashMap<Char, Int>().apply {
        putIfAbsent('R', 1)
        putIfAbsent('P', 2)
        putIfAbsent('S', 3)
    }

    fun practice() {
        input = readInput("day02/Practice")
        println("Part 1 is ${part01()}")
    }

    fun part01(): Int {
        for (session in input) {
            val player = session.split(delimiter)
            val p0 = rpsMapping.getOrDefault(player[0],'o')
            val p1 = rpsMapping.getOrDefault(player[1],'o')
            val outcome = playGame(p0, p1)
            val outcomeScore = outcomeScoreMapping.getOrDefault(outcome, 0)
            val playedScore = shapeScoreMapping.getOrDefault(p1, 0)
            val roundScore = playedScore.plus(outcomeScore)
            totalScore += roundScore
        }
        return totalScore
    }

    private fun playGame(p1: Char, p2: Char): Char {
        return if (p1 == p2) {
            'D';
        } else if (p1 == 'S' && p2 == 'P') {
            'L';
        } else if (p1 == 'P' && p2 == 'R') {
            'L';
        } else if (p1 == 'R' && p2 == 'S') {
            'L';
        } else {
            'W';
        }
    }
}