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
        teams[player.team]?.minus(1)
            ?: throw IllegalArgumentException("")
    }
}