package mafia.users

import exceptions.*
import mafia.decks.enams.*
import view.View
import kotlin.random.*

/*
* TODO
*  implement logic of host
*/

enum class UserGameStates {
    KILLED, ALIVE, NONE
}

enum class UserLobbyStates {
    SPECTATOR, PLAYER, NONE
}

class User {
    val id: UInt = Random.nextUInt()
    var position: Int = -1
    var isHost: Boolean = false
    var isVoted: Boolean = false
    var role: Roles = Roles.NONE
        private set
    var team: Teams = Teams.NONE
        private set
    var gameState: UserGameStates = UserGameStates.NONE
        private set
    var lobbyState: UserLobbyStates = UserLobbyStates.NONE
        private set


    fun vote (playerPos: Int): Boolean {
        if (gameState != UserGameStates.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }
        if (playerPos == position) {
            throw InvalidInputArgumentException("Player can't for itself")
        }

        View.log("player number $position is voting...")
        View.sendMessage("vote for $playerPos? (false/true)")
        val voteChoice = View.readBoolean()

        isVoted = voteChoice
        return voteChoice
    }

    fun say (time: UInt) {
        if (gameState != UserGameStates.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        View.log("player number $position is saying...")
    }

    fun shoutOut () {
        TODO("Implement logic of suspending")
    }

    fun expose(alivePlayersPos: MutableList<Int>): Int {
        if (gameState != UserGameStates.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        View.log("player number $position is exposing...")
        alivePlayersPos.add(0)
        View.sendMessage("choose player from below or enter 0\n$alivePlayersPos")


        val chosenPlayerPos = View.readInt(alivePlayersPos)
        if (chosenPlayerPos == 0) return 0

        if (chosenPlayerPos == this.position) {
            throw InvalidInputArgumentException("Invalid chosen position: player can't expose itself")
        }

        return chosenPlayerPos
    }

    // for future refactoring
    fun chooseFrom(availableChoices: List<Int>): Int {
        View.sendMessage(
            "Please choose position from below list:\n$availableChoices"
        )

        return View.readInt(availableChoices)
    }


    fun toInMenuState() {
        position = -1
        isHost = false
        isVoted = false
        role = Roles.NONE
        team = Teams.NONE
        gameState = UserGameStates.NONE
        lobbyState = UserLobbyStates.NONE
    }

    fun toSpectatorState() {
        position = -1
        isVoted = false
        role = Roles.NONE
        team = Teams.NONE
        gameState = UserGameStates.NONE
        lobbyState = UserLobbyStates.SPECTATOR
    }

    fun toPlayerState(availablePosition: List<Int>) {
        choosePosition(availablePosition)
        isVoted = false
        role = Roles.NONE
        team = Teams.NONE
        gameState = UserGameStates.NONE
        lobbyState = UserLobbyStates.PLAYER
    }

    fun toAliveState(role: Roles, team: Teams) {
        if (this.lobbyState != UserLobbyStates.PLAYER) {
            throw InvalidStateException("Invalid sate: to became alive user must be player")
        }
        isVoted = false
        this.role = role
        this.team = team
        gameState = UserGameStates.ALIVE
    }

    fun toKilledState() {
        if (this.lobbyState != UserLobbyStates.PLAYER) {
            throw InvalidStateException("Invalid sate: to became killed user must be player")
        }
        isVoted = false
        gameState = UserGameStates.KILLED
    }


    private fun choosePosition(availablePosition: List<Int>) {
        View.sendMessage(
            "Please choose position from below list:\n$availablePosition"
        )

        position = View.readInt(availablePosition)
    }
}