package game.mafia.roles

import game.mafia.users.Player

class ClassicPreSet: PreSet {
    val activeRolesAmount = 3
    val activeRoles = listOf<Pair<Int, Roles>>()

    override fun configure(players: Int) {}

    override fun runNight(players: HashMap<Roles, Player>) {}
}

fun kill(player: Player) {}

fun checkForCom(player: Player) {}

fun checkForMafia(player: Player) {}
