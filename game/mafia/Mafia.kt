package game.mafia

import game.mafia.users.Player

class Mafia(val lobby: Lobby) {

    fun runMafia () {
        giveRoles()

        while(checkRules()) {
            runDay()
            if (checkRules()) break
            runNight()
        }
    }

    fun runDay() {}

    fun runNight() {


    }

    fun checkRules() {}

    // нужно будет подумать как относительно настроек выдавать роли
    fun giveRoles () {}


}