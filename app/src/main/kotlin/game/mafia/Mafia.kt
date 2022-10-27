package game.mafia

import game.mafia.roles.*
import game.mafia.users.*

import kotlin.random.Random
import kotlin.random.nextUInt

/*
* implement interruption of game loop
* so game can be paused
*/

class Mafia(lobbyName: String, moderator: Player) {

    val gameId: UInt = Random.nextUInt()
    val lobby: Lobby
    var preSet: PreSet = ClassicPreSet()

    init {
        lobby = Lobby(lobbyName, Host(moderator))
    }

    fun runMafia() {
        giveRoles()

        while(checkRules()) {
            runDay()
            if (checkRules()) break
            preSet.runNight()
        }
    }

    fun runDay() {}

    fun checkRules():Boolean {}

    fun giveRoles() {}
}