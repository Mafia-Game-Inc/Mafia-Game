package game.mafia.roles

import game.mafia.users.Player

class ClassicPreSet (val mafAmount: Int): PreSet {
    override fun runNight(players: HashMap<Roles, Player>) {}
}

fun kill(player: Player) {}

fun checkForCom(player: Player) {}

fun checkForMafia(player: Player) {}
