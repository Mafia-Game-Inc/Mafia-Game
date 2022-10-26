package game.mafia.roles

import game.mafia.users.Player
import game.mafia.users.UserState

class ClassicPreSet: PreSet {
    val activeRolesAmount = 3
    val activeRoles = listOf<Pair<Int, Roles>>()

    override fun configure(players: Int) {}

    override fun runNight(players: HashMap<Roles, Player>) {}
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