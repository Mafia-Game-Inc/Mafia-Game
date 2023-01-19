package mafia

import mafia.decks.enams.*
import mafia.models.*
import mafia.users.*

fun giveRoles() {
    val randomPositions = (1..DeckSettings.playersAmount)
        .shuffled()
        .take(DeckSettings.activePlayersAmount)
    val iterator = randomPositions.listIterator()

    Lobby.players.onEach {
        it.value.toAliveState(Roles.CITIZEN, Teams.RED)
    }

    //assigning roles to random players
    for (roleData in DeckSettings.activeRoles) {
        for (i in 1..roleData.amount) {
            val playerPos = iterator.next()

            Lobby.players[playerPos]!!.toAliveState(roleData.role, roleData.team)
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

fun kill(user: User) {
    DeckSettings.removePlayer(user)
    user.toKilledState()
}