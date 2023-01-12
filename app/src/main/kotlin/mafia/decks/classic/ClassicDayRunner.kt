package mafia.decks.classic

import mafia.RunnableService
import mafia.decks.kill
import mafia.models.Lobby
import mafia.users.*

//those data must be distinguished in separate class
const val TIME_FOR_SPEECH = 60u
const val TIME_FOR_DEFENSE = 30u

class ClassicDayRunner: RunnableService {
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
                player.say(TIME_FOR_SPEECH)

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
            player.say(TIME_FOR_DEFENSE)
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
        kill(playerToKill)

        println("city voted to kill player at $positionToKill place today")
    }

    private fun toStartingState() {
        exposedPlayers.clear()
        alivePositionsList.clear()
        votes.clear()
        Lobby.players.onEach { it.isVoted = false }
    }
}