package mafia

import mafia.decks.enams.*
import mafia.decks.*
import mafia.models.*
import mafia.users.*

fun giveRoles() {
    val randomPositions = (1..DeckSettings.playersAmount)
        .shuffled()
        .take(DeckSettings.activePlayersAmount)
    val iterator = randomPositions.listIterator()

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

fun determineWinner(): Teams {
    if (DeckSettings.blackPlayers == 0) return Teams.RED
    if (DeckSettings.blackPlayers >= DeckSettings.redPlayers) return Teams.BLACK

    return Teams.NONE
}

fun checkRules(): Boolean {
    // true - continue
    // false - stop
    if (DeckSettings.blackPlayers == 0) return false
    if (DeckSettings.blackPlayers >= DeckSettings.redPlayers) return false
    return true
}

fun killAction(player: Player) {
    kill(player)
    println()
}