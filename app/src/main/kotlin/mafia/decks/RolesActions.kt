package mafia.decks

import mafia.decks.enams.Roles
import mafia.*
import mafia.decks.enams.Teams
import mafia.models.*
import mafia.users.User
import view.View

fun killAction(user: User) {
    kill(user)
    View.sendMessage("Mafia killed player ${user.position}")
}

//for future refactoring
fun sheriffsMove(sheriff: User) {
    TODO("определиться что должен делать метод хода шерифа с условием пере использования " +
            "в других деках")
    val availableChoices = Lobby
        .getAlivePlayersPositions()
        .toMutableList()
    availableChoices.remove(sheriff.position)

    View.sendMessage("Hey, Sheriff, try to find mafia")
    View.sendMessage("Choose from:")
    View.sendMessage("$availableChoices")

    val sheriffChoice = View.readInt(availableChoices)

    //add null check
    val chosenPlayer = Lobby.players[sheriffChoice]!!
    if (checkForMafia(chosenPlayer)) {
        View.sendMessage("You are right")
    } else {
        View.sendMessage("You are wrong")
    }
}

fun godFathersMove(godFather: User) {
    TODO("определиться что должен делать метод хода дона с условием пере использования " +
            "в других деках")
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
    TODO("определиться что должен делать метод хода мафии с условием пере использования " +
            "в других деках")
    val availableChoices = Lobby.getAlivePlayersPositions()

    View.sendMessage("Now it is time to kill")
    View.sendMessage("Choose from:")
    View.sendMessage("$availableChoices")

    val mafiaChoice = View.readInt(availableChoices)
    val playerToKill = Lobby.players[mafiaChoice]!!
    if (playerToKill.team == Teams.RED || DeckSettings.blackPlayers > 1) {
        killAction(playerToKill)
    }
}


fun checkForMafia(player: User): Boolean {
    return player.role == Roles.MAFIA || player.role == Roles.GODFATHER
}

fun checkForSheriff(player: User): Boolean {
    return player.role == Roles.SHERIFF
}