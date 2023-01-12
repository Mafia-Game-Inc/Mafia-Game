package mafia.decks

import mafia.decks.enams.Roles
import mafia.decks.enams.Teams
import mafia.models.*
import mafia.users.Player

fun giveRoles() {
    val randomPos = (1..DeckSettings.playersAmount)
        .shuffled()
        .take(DeckSettings.activePlayersAmount)
    val iterator = randomPos.listIterator()

    Lobby.players.onEach {
        it.role = Roles.CITIZEN
        it.team = Teams.RED
    }

    //assigning roles to random players
    for (roleData in DeckSettings.activeRoles) {
        for (i in 1..roleData.amount) {
            val playerPos = iterator.next()

            Lobby.players
                .find { it.position == playerPos }!!
                .let {
                    it.team = roleData.team
                    it.role = roleData.role
                }
        }
    }
}

fun kill(player: Player) {}