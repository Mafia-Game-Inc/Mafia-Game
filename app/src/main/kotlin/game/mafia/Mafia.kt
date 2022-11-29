package game.mafia

import game.exceptions.*
import game.mafia.roles.*
import game.mafia.users.*

import kotlin.random.Random
import kotlin.random.nextUInt

const val TIME_FOR_SPEECH = 60u
const val TIME_FOR_DEFENSE = 30u

open class Mafia(var gameName: String) {
    val gameId: UInt = Random.nextUInt()
    var players = mutableListOf<Player>()
    var preSet: ClassicPreSet = ClassicPreSet()


    fun startGame() {
        if (players.size < 9) {
            print("Can't start game. There must be 10 players")
            return
        }

        runMafia()
    }

    fun pauseGame() {}

    fun resumeGame() {}


    fun addPlayer(player: Player) {
        if (this.players.contains(player)) {
            throw InvalidInputArgumentException(
                "Invalid player object: that player is already in this lobby"
            )
        }

        if (this.players.size >= 9) {
            player.state = UserState.SPECTATOR
        } else {
            player.state = UserState.ALIVE
            player.choosePosition(freePositions())
        }

        this.players.add(player)
    }

    fun addPlayer(playerId: Int) {}

    //fix
    fun kickPlayer(player: Player) {
        if(!players.remove(player)) {
            throw InvalidInputArgumentException("No such player in lobby")
        }
        player.toDefaultState()
    }

    fun kickPlayer(playerPos: UInt) {
        if (playerPos !in 1u..preSet.playersAmount.toUInt()) {
            throw InvalidInputArgumentException("Position out is bounds")
        }

        val player = players.find { it.position == playerPos } ?:
                    throw InvalidInputArgumentException("No such player in lobby")

        players.remove(player)
        player.toDefaultState()
    }


    fun runMafia() {
        players.sortBy { it.position }
        giveRoles()

        while(checkRules()) {
            runDay()
            if (!checkRules()) break
            preSet.runNight(players)
        }

        if (preSet.blackPlayers == 0) {
            println("Red city win!")
        } else {
            println("Black city win!")
        }
    }

    fun runDay() {
        val alivePositionsList = this.alivePlayersPos()
        val exposedPlayers = mutableListOf<Player>()
        val votes = MutableList(players.size) { 0 }

        for (player in players) {
            if (player.state == UserState.ALIVE) {
                player.say(TIME_FOR_SPEECH)

                val chosenPos = player.expose(alivePositionsList)
                if (chosenPos == 0u) continue

                val exposedPlayer = this.players.find { it.position == chosenPos }!!
                exposedPlayers.add(exposedPlayer)
                alivePositionsList.remove(chosenPos)
            }
        }

        for (player in exposedPlayers) {
            player.say(TIME_FOR_DEFENSE)
        }

        for (exposed in exposedPlayers) {
            if (exposed === exposedPlayers.last()) {
                val nonVoted = players.count { !it.isVoted }
                votes[exposed.position.toInt() - 1] = nonVoted
            }

            for (player in players) {
                if (player.state == UserState.ALIVE && player != exposed && !player.isVoted) {
                    if (player.vote(exposed.position)) votes[exposed.position.toInt() - 1]++
                }
            }
        }

        val positionToKill = votes.indexOf(votes.maxOrNull()).toUInt() + 1u
        val playerToKill = players.find {
            it.position == positionToKill
        }!!
        preSet.kill(playerToKill)

        println("city voted to kill player at $positionToKill place today")

        players.onEach { it.isVoted = false }
    }

    // true - continue
    // false - stop
    fun checkRules():Boolean {
        val red = preSet.redPlayers
        val black = preSet.blackPlayers

        if (black == 0) return false
        if (black >= red) return false
        return true
    }

    fun giveRoles() {
        val randomPos = (1u..9u).shuffled().take(preSet.activePlayersAmount)
        val iterator = randomPos.listIterator()

        players.onEach {
            it.role = Roles.CITIZEN
            it.team = Teams.RED
        }

        for (roleData in preSet.activeRoles) {
            for (i in 1..roleData.amount) {
                val playerPos = iterator.next()

                players.find { it.position == playerPos }?.let {
                    it.team = roleData.team
                    it.role = roleData.role
                }
            }
        }
    }

    fun alivePlayersPos(): MutableList<UInt> {
        val positions = mutableListOf<UInt>()
        for (player in this.players) {
            if (player.state == UserState.ALIVE) {
                positions.add(player.position)
            }
        }
        return positions
    }

    fun freePositions(): List<UInt> {
        val pos = (1u..9u).toMutableList()

        for (player in players) {
            if (player.state == UserState.ALIVE) {
                pos.remove(player.position)
            }
        }

        return pos.toList()
    }
}