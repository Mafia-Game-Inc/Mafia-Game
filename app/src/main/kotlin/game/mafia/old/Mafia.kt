package game.mafia.old

import game.exceptions.*
import game.mafia.old.roles.*
import game.mafia.old.roles.presets.*
import game.mafia.old.users.*

import kotlin.random.Random
import kotlin.random.nextUInt

const val TIME_FOR_SPEECH = 60u
const val TIME_FOR_DEFENSE = 30u

class Mafia(var gameName: String, presetType: String = "Classic") {
    val gameId: UInt = Random.nextUInt()
    private val players = mutableListOf<Player>()
    private var preset: Preset = Classic()
    var gameState: GameStates = GameStates.NOT_STARTED
        private set

    init {
        when (presetType) {
            "Classic" -> preset = Classic()
            else -> throw InvalidInputArgumentException("Invalid preset type: no such presets available")
        }
    }

    fun startGame() {
        if (players.size < preset.playersAmount) {
            print("Can't start game. There must be 10 players")
            return
        }

        runMafia()
    }

    fun pauseGame() {}

    fun resumeGame() {}

    fun addPlayer(player: Player) {
        if (players.contains(player)) {
            throw InvalidInputArgumentException(
                "Invalid player object: that player is already in this lobby"
            )
        }

        if (players.size >= preset.playersAmount) {
            player.toSpectatorState()
        } else {
            player.toAliveState()
            player.choosePosition(freePositions())
        }

        players.add(player)
    }

    fun removePlayer(player: Player) {
        if (player.state == UserState.ALIVE && gameState == GameStates.RUNNING) {
            throw InvalidStateException ("Alive player can't be removed when game is running")
        }

        if(!players.remove(player)) {
            throw InvalidInputArgumentException("No such player in lobby")
        }
        player.toDefaultState()
    }

    fun changePreset(type: String): Preset {
        TODO("using switch or kotlin analog return needed object or trow exception")
    }


    private fun runMafia() {
        players.sortBy { it.position }
        giveRoles()

        while(checkRules()) {
            runDay()
            if (!checkRules()) break
            preset.runNight(players)
        }

        if (preset.blackPlayers == 0) {
            println("Red city win!")
        } else {
            println("Black city win!")
        }
    }

    private fun runDay() {
        val alivePositionsList = alivePlayersPos().toMutableList()
        val exposedPlayers = mutableListOf<Player>()
        val votes = MutableList(players.size) { 0 }

        //speech and exposing loop
        for (player in players) {
            if (player.state == UserState.ALIVE) {
                player.say(TIME_FOR_SPEECH)

                val chosenPos = player.expose(alivePositionsList)
                if (chosenPos == 0) continue

                val exposedPlayer = this.players.find { it.position == chosenPos }!!
                exposedPlayers.add(exposedPlayer)
                alivePositionsList.remove(chosenPos)
            }
        }

        //defense loop
        for (player in exposedPlayers) {
            player.say(TIME_FOR_DEFENSE)
        }

        //voting loop
        for (exposed in exposedPlayers) {
            if (exposed === exposedPlayers.last()) {
                val nonVoted = players.count { !it.isVoted }
                votes[exposed.position - 1] = nonVoted
            }

            for (player in players) {
                if (player.state == UserState.ALIVE && player != exposed && !player.isVoted) {
                    if (player.vote(exposed.position)) votes[exposed.position - 1]++
                }
            }
        }

        //determine what player to kill
        val positionToKill = votes.indexOf(votes.maxOrNull()) + 1
        val playerToKill = players.find {
            it.position == positionToKill
        }!!
        kill(playerToKill)

        println("city voted to kill player at $positionToKill place today")

        players.onEach { it.isVoted = false }
    }

    private fun checkRules():Boolean {
        // true - continue
        // false - stop

        val red = preset.redPlayers
        val black = preset.blackPlayers

        if (black == 0) return false
        if (black >= red) return false
        return true
    }

    private fun giveRoles() {
        val randomPos = (1..preset.playersAmount).shuffled().take(preset.activePlayersAmount)
        val iterator = randomPos.listIterator()

        players.onEach {
            it.role = Roles.CITIZEN
            it.team = Teams.RED
        }

        //assigning roles to random players
        for (roleData in preset.activeRoles) {
            for (i in 1..roleData.amount) {
                val playerPos = iterator.next()

                players
                    .find { it.position == playerPos }!!
                    .let {
                    it.team = roleData.team
                    it.role = roleData.role
                }
            }
        }
    }

    private fun kill(player: Player) {
        preset.removePlayer(player)
        player.toKilledState()
    }

    private fun alivePlayersPos(): List<Int> {
        val positions = mutableListOf<Int>()

        for (player in this.players) {
            if (player.state == UserState.ALIVE) {
                positions.add(player.position)
            }
        }

        return positions
    }

    private fun freePositions(): List<Int> {
        val pos = (1..preset.playersAmount).toMutableList()

        for (player in players) {
            if (player.state == UserState.ALIVE) {
                pos.remove(player.position)
            }
        }

        return pos.toList()
    }
}