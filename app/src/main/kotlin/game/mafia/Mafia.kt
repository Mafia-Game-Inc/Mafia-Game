package game.mafia

import game.mafia.roles.*
import game.mafia.users.*

import kotlin.random.Random
import kotlin.random.nextUInt

/*
* implement interruption of game loop
* so game can be paused
*/

const val TIME_FOR_SPEECH = 60u
const val TIME_FOR_DEFENSE = 30u

class Mafia(var gameName: String) {
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

    fun addPlayer(player: Player) {
        if (this.players.size >= 9) {
            player.state = UserState.SPECTATOR
        } else {
            player.state = UserState.ALIVE
        }

        val playerPos = readln().toUInt()
        //check if this position is not already busy
        player.position = playerPos

        players.add(player)
    }

    fun kickPlayer(player: Player) {
        player.toDefaultState()
        players.remove(player)
    }

    fun kickPlayer(playerPos: UInt) {
        val player = players.find { it.position == playerPos }

        player?.toDefaultState()
        players.remove(player)
    }


    fun runMafia() {
        giveRoles()

        while(checkRules()) {
            runDay()
            if (checkRules()) break
            preSet.runNight(players)
        }

        if (this.preSet.blackPlayers == 0) {
            println("Red city win!")
        } else {
            println("Black city win!")
        }
    }

    fun runDay() {
        val exposedPlayers = mutableListOf<Player>()
        val votes = MutableList(players.size) { 0u }
        var exposedPlayer: Player

        for (player in players) {
            if (player.state == UserState.ALIVE) {
                player.say(TIME_FOR_SPEECH)

                exposedPlayer = player.expose(players)!!
                exposedPlayers.add(exposedPlayer)
            }
        }

        for (player in players) {
            if (player.state == UserState.ALIVE) {
                player.say(TIME_FOR_DEFENSE)
            }
        }

        for (exposed in exposedPlayers) {
            for (player in players) {
                if (player.state == UserState.ALIVE) {
                    if (player.vote(exposed.position)) votes[exposed.position.toInt() - 1]++
                }
            }
        }

        kickPlayer(votes.indexOf(votes.maxOrNull()).toUInt())
    }

    fun checkRules():Boolean {
        return preSet.blackPlayers != preSet.redPlayers
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
}