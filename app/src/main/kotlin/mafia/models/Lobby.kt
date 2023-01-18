package mafia.models

import mafia.users.*
import view.View
import java.util.*

object Lobby {
    val players = TreeMap<Int, User>(Comparator.comparingInt { it })
    val spectators = mutableSetOf<User>()
    val allUsers = mutableSetOf<User>()


    fun addUser(user: User) {
        if (allUsers.contains(user)) {
            View.sendMessage("you already in this game")
        }

        if (players.size >= DeckSettings.playersAmount) {
            user.toSpectatorState()
            spectators.add(user)
        } else {
            user.toPlayerState(getUnoccupiedPositions())
            players[user.position] = user
        }

        allUsers.add(user)
    }

    fun getAlivePlayersPositions(): List<Int> {
        val positions = mutableListOf<Int>()

        for (player in this.players) {
            if (player.value.gameState == UserGameStates.ALIVE) {
                positions.add(player.value.position)
            }
        }

        return positions
    }

    fun getUnoccupiedPositions(): List<Int> {
        val maxPosition = DeckSettings.playersAmount
        val positions = (1..maxPosition).toMutableList()

        for (player in players) {
            if (player.value.gameState == UserGameStates.ALIVE) {
                positions.remove(player.value.position)
            }
        }

        return positions
    }
}