package mafia.decks.classic

import exceptions.*
import mafia.RunnableService
import mafia.decks.enams.*
import mafia.decks.*
import mafia.models.*
import mafia.users.*
import view.View

class ClassicNightRunner: RunnableService {
    override fun run() {
        val activePlayers = Lobby.getActivePlayers()
        if (activePlayers.isEmpty()) {
            throw InvalidStateException("Invalid game state: roles has not been given")
        }

/*        add null check
        val sheriff = activePlayers[Roles.SHERIFF]!!
        if (sheriff.gameState == UserGameStates.ALIVE) {
            sheriffsMove(sheriff)
        }

        val godFather = activePlayers[Roles.GODFATHER]!!
        val mafias = Lobby
            .getActivePlayers()
            .filterKeys { it == Roles.MAFIA }
            .filterValues { it.gameState == UserGameStates.ALIVE }
            .toList()

        if (godFather.gameState == UserGameStates.ALIVE) {
            mafiaMove(godFather)
        } else if (mafias.isNotEmpty()) {
            mafiaMove(mafias.first().second)
        }

        if (godFather.gameState == UserGameStates.ALIVE) {
            godFathersMove(godFather)
        }*/
        val sheriff = activePlayers[Roles.SHERIFF]!!
        if (sheriff.gameState == UserGameStates.ALIVE) {
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

        val aliveMafias = activePlayers
            .filterValues { it.team == Teams.BLACK && it.gameState == UserGameStates.ALIVE }
            .toList()
        if (aliveMafias.isNotEmpty()) {
            val availableChoices = Lobby.getAlivePlayersPositions()

            View.sendMessage("Now it is time to kill")
            View.sendMessage("Choose from:")
            View.sendMessage("$availableChoices")

            val mafiaChoice = View.readInt(availableChoices)
            val playerToKill = Lobby.players[mafiaChoice]!!
            if (playerToKill.team == Teams.RED || DeckSettings.blackPlayers > 1) {
                kill(playerToKill)
            }
        }

        val godFather = activePlayers[Roles.GODFATHER]!!
        if (godFather.gameState == UserGameStates.ALIVE) {
            val availableChoices = Lobby
                .getAlivePlayersPositions()
                .toMutableList()
            availableChoices.removeAll(
                activePlayers
                    .filter { it.value.team == Teams.BLACK }
                    .flatMap { listOf(it.value.position) }
            )

            View.sendMessage("Hey, Don, try to find Sheriff")
            View.sendMessage("Choose from:")
            View.sendMessage("$availableChoices")

            val godfatherChoice = View.readInt(availableChoices)

            //add null check
            val chosenPlayer = Lobby.players[godfatherChoice]!!
            if (checkForSheriff(chosenPlayer)) {
                View.sendMessage("You are right")
            } else {
                View.sendMessage("You are wrong")
            }
        }
    }
}