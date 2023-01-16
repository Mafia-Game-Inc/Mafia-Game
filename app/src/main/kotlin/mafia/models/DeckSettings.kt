package mafia.models

import exceptions.InvalidInputArgumentException
import mafia.decks.enams.Teams
import mafia.users.Player

object DeckSettings {
    var playersAmount = -1
    var activePlayersAmount = -1
    var redPlayers = -1
    var blackPlayers = -1
    var activeRoles = listOf<RoleData>()

    fun removePlayer(player: Player) {
        when(player.team) {
            Teams.RED -> redPlayers--
            Teams.BLACK -> blackPlayers--
            Teams.NONE ->
                throw InvalidInputArgumentException(
                    "player ${player.id} has not initialized team property"
                )
        }
    }
}