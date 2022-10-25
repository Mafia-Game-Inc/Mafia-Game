package game.mafia

import game.mafia.roles.ClassicPreSet
import game.mafia.roles.PreSet
import game.mafia.users.Host
import game.mafia.users.Player
import kotlin.random.Random
import kotlin.random.nextUInt

class Mafia(lobbyName: String, moderator: Player) {

    val gameId: UInt = Random.nextUInt()
    val lobby: Lobby
    var preSet: PreSet = ClassicPreSet()

    init {
        lobby = Lobby(lobbyName, Host(moderator))
    }

    fun runMafia() {
        /*giveRoles()

        while(checkRules()) {
            runDay()
            if (checkRules()) break
            preSet.runNight()
        }*/
    }

    fun runDay() {}

    fun checkRules():Boolean {}

    fun giveRoles() {}
}