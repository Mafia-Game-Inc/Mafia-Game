package game.mafia.roles

import game.mafia.users.Player
import game.mafia.users.UserState

import java.util.Scanner

interface PreSet {

    fun configure(players: Int)

    fun runNight(players: Array<Player>) {
        for (player in players) {
            if (player.role == Roles.SHERIFF && player.state == UserState.ALIVE) {
                println("Hey, Sheriff, try to find mafia")
                val reader = Scanner(System.`in`)
                var integer:Int = reader.nextInt()
                //Check for validity of input (integer >= 1)
                checkForMafia(players[integer - 1])
            }
        }
        for (player in players) {
            if (player.role == Roles.GODFATHER && player.state == UserState.ALIVE) {
                println("Hey, Don, try to find Sheriff")
                val reader = Scanner(System.`in`)
                var integer:Int = reader.nextInt()
                //Check for validity of input (integer >= 1)
                checkForCom(players[integer-1])
            }
        }
        println("Now, choose to kill!")
        val reader = Scanner(System.`in`)
        var integer:Int = reader.nextInt()
        kill(players[integer - 1])
        var position = integer - 1
        println("Player $position was killed")
    }
}