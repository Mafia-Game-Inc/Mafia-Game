package game.mafia.old.roles.presets

import game.mafia.old.roles.*
import game.mafia.old.users.*

class Classic: Preset() {
    override var playersAmount = 9
    override var redPlayers = 6
    override var blackPlayers = 3
    override var activePlayersAmount = 4
    override var activeRoles = listOf(
        RoleData(2, Teams.BLACK, Roles.MAFIA),
        RoleData(1, Teams.BLACK, Roles.GODFATHER),
        RoleData(1, Teams.RED, Roles.SHERIFF)
    )

    override fun configure() {}

    override fun runNight(players: MutableList<Player>) {
        val availablePositions = mutableListOf<Int>()

        for (player in players) {
            if (player.state == UserState.ALIVE) {
                availablePositions.add(player.position)
            }
        }

        val sheriff = players.find { it.role == Roles.SHERIFF && it.state == UserState.ALIVE }
        if (sheriff != null) checkForMafia(sheriff, players, availablePositions)

        val blackTeam = players.filter {it.team == Teams.BLACK && it.state == UserState.ALIVE}
        kill(blackTeam, players)

        val godFather = players.find { it.role == Roles.GODFATHER && it.state == UserState.ALIVE }
        if (godFather != null) checkForCom(godFather, players)

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
            it.position == mafiaChoice
        }!!
        if (blackPlayers == 1 && playerToKill.team == Teams.BLACK) return

        super.removePlayer(playerToKill)
        println("Player $mafiaChoice was killed tonight")
    }

//    fun kill(player: Player) {
//        player.state = UserState.KILLED
//        if (player.team == Teams.RED) { redPlayers-- }
//        else { blackPlayers-- }
//    }
//
//    fun checkForCom(player: Player) {
//        if (player.role == Roles.SHERIFF) {
//            println("You are right!")
//        }
//        else println("You are wrong!")
//    }
//
//    fun checkForMafia(player: Player) {
//        if (player.role == Roles.MAFIA || player.role == Roles.GODFATHER) {
//            println("You are right!")
//        }
//        else println("You are wrong!")
//    }
}

