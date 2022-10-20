package game.mafia

import game.mafia.roles.*

class Mafia(
    val gameId: Int,
    val lobby: Lobby,
    val preSet: PreSet = ClassicPreSet()
) {

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