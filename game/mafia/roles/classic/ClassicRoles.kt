package game.mafia.roles.classic

import game.mafia.roles.Role

open class Mafia : Role, Base() {

    private fun kill () {}

    override fun utilityUse() {
        kill()
        println("Kill player...")
    }
}

class GodFather : Role, Mafia() {
    fun checkForCom() {
        println("Check for commissar...")
    }
}

class Commissar : Role {
    override fun utilityUse() {
        println("Check for mafia...")
    }
}

