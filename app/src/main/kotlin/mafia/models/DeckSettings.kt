package mafia.models

import exceptions.InvalidInputArgumentException
import mafia.decks.enams.Teams
import mafia.users.User

object DeckSettings {
    var playersAmount = -1
    var activePlayersAmount = -1
    var redPlayers = -1
    var blackPlayers = -1
    var activeRoles = listOf<RoleData>()

    fun removePlayer(user: User) {
        when(user.team) {
            Teams.RED -> redPlayers--
            Teams.BLACK -> blackPlayers--
            Teams.NONE ->
                throw InvalidInputArgumentException(
                    "player ${user.id} has not initialized team property"
                )
        }
    }
}