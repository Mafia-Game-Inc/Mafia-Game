package mafia.day.defaultDay

import mafia.*
import mafia.day.hangPlayer
import mafia.models.*
import mafia.users.*

class DefaultDayRunner: RunnableService {
    private val exposedUsers = mutableListOf<User>()
    private var alivePositionsList = mutableListOf<Int>()
    private var votes = mutableListOf<Int>()

    override fun run() {
        alivePositionsList = Lobby.getAlivePlayersPositions().toMutableList()
        votes = MutableList(Lobby.players.size) { 0 }

        speechAndExposingLoop()
        defenceLoop()
        votingLoop()
        determineAndHangPlayer()

        toStartingState()
    }

    private fun speechAndExposingLoop() {
        for (player in Lobby.players) {
            if (player.value.gameState == UserGameStates.ALIVE) {
                player.value.say(DaySettings.timeForSpeech)

                val chosenPos = player.value.expose(alivePositionsList)
                if (chosenPos == 0) continue

                val exposedPlayer = Lobby.players[chosenPos]!!
                exposedUsers.add(exposedPlayer)
                alivePositionsList.remove(chosenPos)
            }
        }
    }

    private fun defenceLoop() {
        for (player in exposedUsers) {
            player.say(DaySettings.timeForDefence)
        }
    }

    private fun votingLoop() {
        for (exposed in exposedUsers) {
            if (exposed === exposedUsers.last()) {
                val nonVoted = Lobby.players.count { !it.value.isVoted }
                votes[exposed.position - 1] = nonVoted
            }

            for (player in Lobby.players) {
                if (
                    player.value.gameState == UserGameStates.ALIVE
                    && player.value != exposed && !player.value.isVoted
                ) {
                    if (player.value.vote(exposed.position)) votes[exposed.position - 1]++
                }
            }
        }
    }

    private fun determineAndHangPlayer() {
        val positionToKill = votes.indexOf(votes.maxOrNull()) + 1
        val playerToKill = Lobby.players[positionToKill]!!

        hangPlayer(playerToKill)
    }

    private fun toStartingState() {
        exposedUsers.clear()
        alivePositionsList.clear()
        votes.clear()
        Lobby.players.onEach { it.value.isVoted = false }
    }
}