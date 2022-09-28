package game.mafia.roles

import game.mafia.users.Player

open class Mafia(base: Player) : Player(base.position, base.id) , ActiveRole {
    override fun utilityUse() {
        println("Kill player...")
    }
}

class GodFather(base: Player) : Mafia(base) {
    fun checkForCom() {
        println("Check for commissar...")
    } // проблему с невозможностью вызова через ссылку ActiveRole этого метода
    // можно решить объединив в один метод и убийство и проверку, но! это КОСТЫЛЬ некрасивый
}

class Commissar(base: Player) : Player(base.position, base.id) , ActiveRole {
    override fun utilityUse() {
        println("Check for mafia...")
    }
}

