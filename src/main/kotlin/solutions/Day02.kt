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
    private val handOutcomeMapping = HashMap<Char, Char>().apply {
        putIfAbsent('X', 'L')
        putIfAbsent('Y', 'D')
        putIfAbsent('Z', 'W')
    }

    fun practice() {
        input = readInput("day02/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        totalScore = 0
        for (session in input) {
            val player = session.split(delimiter)
            val p0 = rpsMapping.getOrDefault(player[0], 'o')
            val p1 = rpsMapping.getOrDefault(player[1], 'o')
            val outcome = playGame(p0, p1)
            val outcomeScore = outcomeScoreMapping.getOrDefault(outcome, 0)
            val playedScore = shapeScoreMapping.getOrDefault(p1, 0)
            val roundScore = playedScore.plus(outcomeScore)
            totalScore += roundScore
        }
        return totalScore
    }

    fun part02(): Int {
        totalScore = 0
        for (session in input) {
            val player = session.split(delimiter)
            val p0 = rpsMapping.getOrDefault(player[0], 'o')
            val p1 = player[1].single()
            val hand = playHand(p0, p1)
            val handOutcome = handOutcomeMapping.getOrDefault(p1, 0)
            val playedScore = shapeScoreMapping.getOrDefault(hand, 0)
            val outcomeScore = outcomeScoreMapping.getOrDefault(handOutcome, 0)
            val roundScore = playedScore.plus(outcomeScore)
            totalScore += roundScore
        }
        return totalScore
    }

    private fun playHand(p1: Char, p2: Char): Char {
        return when (p2) {
            'X' -> {//Lose
                when (p1) {
                    'R' -> {
                        'S'
                    }

                    'S' -> {
                        'P'
                    }

                    else -> {
                        'R'
                    }
                }
            }

            'Z' -> {//Win
                when (p1) {
                    'R' -> {
                        'P'
                    }

                    'S' -> {
                        'R'
                    }

                    else -> {
                        'S'
                    }
                }
            }

            else -> {//Draw
                p1
            }
        }
    }

    private fun playGame(p1: Char, p2: Char): Char {
        return if (p1 == p2) {
            'D'
        } else if (p1 == 'S' && p2 == 'P') {
            'L'
        } else if (p1 == 'P' && p2 == 'R') {
            'L'
        } else if (p1 == 'R' && p2 == 'S') {
            'L'
        } else {
            'W'
        }
    }
}