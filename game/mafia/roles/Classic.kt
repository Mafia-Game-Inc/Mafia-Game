package game.mafia.roles

import game.mafia.users.Player

class ClassicPreSet: PreSet {
    override fun runNight(players: HashMap<Roles, Player>) {

    }
}

fun kill(player: Player) {
    TODO("Write logic of killing")
}

fun checkForCom(player: Player) {
    TODO("Write logic of checking for com")
}

fun checkForMafia(player: Player) {
    TODO("Write logic of checking for mafia")
}
