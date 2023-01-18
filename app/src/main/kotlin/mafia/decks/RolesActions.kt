package mafia.decks

import mafia.decks.enams.Roles
import mafia.models.*
import mafia.users.User
import view.View

fun kill(user: User) {
    DeckSettings.removePlayer(user)
    user.toKilledState()
}

//for future refactoring
fun sheriffsMove(sheriff: User) {
    View.sendMessage("Hey, Sheriff, try to find mafia")

    val availableChoices = Lobby
        .getAlivePlayersPositions()
        .toMutableList()
    availableChoices.remove(sheriff.position)
    val sheriffChoice = View.readInt(availableChoices)

    //add null check
    val chosenPlayer = Lobby.players[sheriffChoice]

    if (chosenPlayer?.role == Roles.MAFIA || chosenPlayer?.role == Roles.GODFATHER) {
        View.sendMessage("You are right")
    } else {
        View.sendMessage("You are wrong")
    }
}

fun godFathersMove(godFather: User) {
    View.sendMessage("Hey, God father, try to find mafia")

    val availableChoices = Lobby
        .getAlivePlayersPositions()
        .toMutableList()
    availableChoices.remove(godFather.position)
    val godFathersChoice = View.readInt(availableChoices)

    //add null check
    val chosenPlayer = Lobby.players[godFathersChoice]

    if (chosenPlayer?.role == Roles.SHERIFF) {
        View.sendMessage("You are right")
    } else {
        View.sendMessage("You are wrong")
    }
}

fun mafiaMove(godFather: User): Int {
    TODO("Not implement")
}


fun checkForMafia(player: User): Boolean {
    return player.role == Roles.MAFIA || player.role == Roles.GODFATHER
}

fun checkForSheriff(player: User): Boolean {
    return player.role == Roles.SHERIFF
}