package mafia.decks

import mafia.decks.enams.*
import mafia.models.*
import mafia.models.DeckSettings
import mafia.users.Player

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

fun kill(player: Player) {
    DeckSettings.removePlayer(player)
    player.toKilledState()
}

fun determineWinner(): Teams {
    for(team in DeckSettings.teams) {
        if (team.key != Teams.RED && team.value >= DeckSettings.playersAmount) {
            return team.key
        } else {

        }
    }

    return Teams.NONE
}