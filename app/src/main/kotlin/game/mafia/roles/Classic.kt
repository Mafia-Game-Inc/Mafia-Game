package game.mafia.roles

import game.mafia.users.*
import java.util.*

/*
* 1. захардкоженый вариант просто чтоб работал
* */

class ClassicPreSet {
    var redPlayers = 7
    var blackPlayers = 3
    var activeRoles = listOf(
        Pair(2, Pair(Teams.BLACK, Roles.MAFIA)),
        Pair(1, Pair(Teams.BLACK, Roles.GODFATHER)),
        Pair(1, Pair(Teams.RED, Roles.SHERIFF))
    )

    fun runNight(players: MutableList<Player>) {
        for (player in players) {
            if (player.role == Roles.SHERIFF && player.state == UserState.ALIVE) {
                println("Hey, Sheriff, try to find mafia")

                val reader = Scanner(System.`in`)
                val integer = reader.nextInt()

                //Check for validity of input (integer >= 1)
                checkForMafia(players[integer - 1])
            }
        }

        for (player in players) {
            if (player.role == Roles.GODFATHER && player.state == UserState.ALIVE) {
                println("Hey, Don, try to find Sheriff")

                val reader = Scanner(System.`in`)
                val integer = reader.nextInt()

                //Check for validity of input (integer >= 1)
                checkForCom(players[integer-1])
            }
        }

        println("Now, choose to kill!")

        val reader = Scanner(System.`in`)
        val integer = reader.nextInt()

        kill(players[integer - 1])
        val position = integer - 1
        println("Player $position was killed")
    }
}

fun kill(player: Player) {
    player.state = UserState.KILLED
}

fun checkForCom(player: Player) {
    if (player.role == Roles.SHERIFF) {
        println("You are right!")
    }
    else println("You are wrong!")
}

fun checkForMafia(player: Player) {
    if (player.role == Roles.MAFIA || player.role == Roles.GODFATHER) {
        println("You are right!")
    }
    else println("You are wrong!")
}