package game.mafia.roles

import game.mafia.users.*
import java.util.*

class ClassicPreSet {
    var playersAmount = 9
    var redPlayers = 6
    var blackPlayers = 3
    var activePlayersAmount = 4
    var activeRoles = listOf(
        RoleData(2, Teams.BLACK, Roles.MAFIA),
        RoleData(1, Teams.BLACK, Roles.GODFATHER),
        RoleData(1, Teams.RED, Roles.SHERIFF)
    )

    fun configure() {}

    fun runNight(players: MutableList<Player>) {
        for (player in players) {
            if (player.role == Roles.SHERIFF && player.state == UserState.ALIVE) {
                println("Hey, Sheriff, try to find mafia")

                val sheriffChoice = readln().toInt()

                //Check for validity of input (integer >= 1)
                checkForMafia(players[sheriffChoice - 1])
            }
        }

        for (player in players) {
            if (player.role == Roles.GODFATHER && player.state == UserState.ALIVE) {
                println("Hey, Don, try to find Sheriff")

                val godfatherChoice = readln().toInt()

                //Check for validity of input (integer >= 1)
                checkForCom(players[godfatherChoice-1])
            }
        }

        println("Now, choose to kill!")
        val mafiaChoice = readln().toInt()

        //check user input if needed ask him to enter again

        val playerToKill = players.find {
            it.position == mafiaChoice.toUInt()
        }!!
        if (blackPlayers == 1 && playerToKill.team == Teams.BLACK) return

        kill(playerToKill)
        println("Player $mafiaChoice was killed tonight")
    }


    fun kill(player: Player) {
        player.state = UserState.KILLED
        if (player.team == Teams.RED) { redPlayers-- }
        else { blackPlayers-- }
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
}

