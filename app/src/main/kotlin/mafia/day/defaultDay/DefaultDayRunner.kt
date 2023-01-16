package mafia.day.defaultDay

import mafia.*
import mafia.day.hangPlayer
import mafia.models.*
import mafia.users.*

class DefaultDayRunner: RunnableService {
    private val exposedPlayers = mutableListOf<Player>()
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
            if (player.state == PlayerState.ALIVE) {
                player.say(DaySettings.timeForSpeech)

                val chosenPos = player.expose(alivePositionsList)
                if (chosenPos == 0) continue

                val exposedPlayer = Lobby.players.find { it.position == chosenPos }!!
                exposedPlayers.add(exposedPlayer)
                alivePositionsList.remove(chosenPos)
            }
        }
    }

    private fun defenceLoop() {
        for (player in exposedPlayers) {
            player.say(DaySettings.timeForDefence)
        }
    }

    private fun votingLoop() {
        for (exposed in exposedPlayers) {
            if (exposed === exposedPlayers.last()) {
                val nonVoted = Lobby.players.count { !it.isVoted }
                votes[exposed.position - 1] = nonVoted
            }

            for (player in Lobby.players) {
                if (player.state == PlayerState.ALIVE && player != exposed && !player.isVoted) {
                    if (player.vote(exposed.position)) votes[exposed.position - 1]++
                }
            }
        }
    }

    private fun determineAndHangPlayer() {
        val positionToKill = votes.indexOf(votes.maxOrNull()) + 1
        val playerToKill = Lobby.players.find {
            it.position == positionToKill
        }!!

        hangPlayer(playerToKill)
    }

    private fun toStartingState() {
        exposedPlayers.clear()
        alivePositionsList.clear()
        votes.clear()
        Lobby.players.onEach { it.isVoted = false }
    }
}