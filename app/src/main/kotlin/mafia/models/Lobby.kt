package mafia.models

import mafia.users.Player
import mafia.users.PlayerState
import java.util.*

object Lobby {
    val players = TreeSet<Player>(Comparator.comparingInt { it.position })
//    val players = mutableListOf<Player>()

    fun getAlivePlayersPositions(): List<Int> {
        val positions = mutableListOf<Int>()

        for (player in this.players) {
            if (player.state == PlayerState.ALIVE) {
                positions.add(player.position)
            }
        }

        return positions
    }

    fun getUnoccupiedPositions(maxPosition: Int): List<Int> {
        val pos = (1..maxPosition).toMutableList()

        for (player in players) {
            if (player.state == PlayerState.ALIVE) {
                pos.remove(player.position)
            }
        }

        return pos
    }
}