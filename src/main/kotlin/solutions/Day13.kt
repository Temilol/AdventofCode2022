package solutions

import utils.readInput
import kotlin.math.min

class Day13 {

    private var input = readInput("day13/Day13")
    private lateinit var packets: List<Packet>

    fun practice() {
        input = readInput("day13/Practice")
        println("Part 1 is ${part01()}")
        println("Part 2 is ${part02()}")
    }

    fun part01(): Int {
        preload()
        val sum = packets.chunked(2).mapIndexed { index, pair ->
            if (pair.first() < pair.last()) index + 1 else 0
        }.sum()
        postLoad()
        return sum
    }

    fun part02(): Int {
        preload()
        val dividerPacket1 = Packet.of("[[2]]")
        val dividerPacket2 = Packet.of("[[6]]")
        val ordered = (packets + dividerPacket1 + dividerPacket2).sorted()
        val soln = (ordered.indexOf(dividerPacket1) + 1) * (ordered.indexOf(dividerPacket2) + 1)
        postLoad()
        return soln
    }

    private fun preload() {
        packets = input.filter { it.isNotBlank() }.map { Packet.of(it) }
    }

    private fun postLoad() {
        packets.toMutableList().clear()
    }


    private sealed class Packet : Comparable<Packet> {
        companion object {
            fun of(input: String): Packet =
                of(
                    input.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
                        .filter { it.isNotBlank() }
                        .filter { it != "," }
                        .iterator()
                )

            private fun of(input: Iterator<String>): Packet {
                val packets = mutableListOf<Packet>()
                while (input.hasNext()) {
                    when (val symbol = input.next()) {
                        "]" -> return ListPacket(packets)
                        "[" -> packets.add(of(input))
                        else -> packets.add(IntPacket(symbol.toInt()))
                    }
                }
                return ListPacket(packets)
            }
        }
    }

    private class IntPacket(val amount: Int) : Packet() {
        fun asList(): Packet = ListPacket(listOf(this))

        override fun compareTo(other: Packet): Int =
            when (other) {
                is IntPacket -> amount.compareTo(other.amount)
                is ListPacket -> asList().compareTo(other)
            }
    }

    private class ListPacket(val subPackets: List<Packet>) : Packet() {
        override fun compareTo(other: Packet): Int =
            when (other) {
                is IntPacket -> compareTo(other.asList())
                is ListPacket -> subPackets.zip(other.subPackets)
                    .map { it.first.compareTo(it.second) }
                    .firstOrNull { it != 0 } ?: subPackets.size.compareTo(other.subPackets.size)
            }
    }
}