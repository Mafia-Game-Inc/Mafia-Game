package mafia.models

import game.exceptions.InvalidInputArgumentException
import mafia.decks.enams.Teams
import mafia.users.Player

object DeckSettings {
    var playersAmount = -1
    var teams = hashMapOf<Teams, Int>()
    var activePlayersAmount = -1
    var activeRoles = listOf<RoleData>()

    fun removePlayer(player: Player) {
        var amount = teams[player.team]
        amount -= 1 ?: throw InvalidInputArgumentException("No such players in lobby")
    }
}