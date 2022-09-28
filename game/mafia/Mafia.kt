package game.mafia

import game.mafia.roles.ActiveRole
import game.mafia.users.Player

/*
* возможно решить проблему с ролями получиться
* тем что в пекедже ролей с нужных касах прописывать статические методы
* куда просто передовать объект игрока и выполнять для него типичные опирации*/

class Mafia {
    // информация о лобби
    var lobbyId: Int = -1
    var lobbyName: String = "NONE"

    // информация об участниках
    var players: MutableList<Player> = mutableListOf() // из ссылки плеер нельзя вызывать метод мафии
    var ative: MutableList<ActiveRole> = mutableListOf() // зачем нужен дублирующий массив только для того
    // чтобы вызвать один метод

    fun gameLoop () {
        giveRoles() // здесь стоит не только раздать роли но и познакомить мафию!

        while(checkRules()) {
            runDay()
            if (checkRules()) break
            runNight()
        }
    }

    fun runDay() {
    }

    fun runNight() {}

    fun checkRules() : Boolean {
        return false;
    }


    fun giveRoles () {
    }

    fun createPreSet (preSet: PreSet, amountOfplayers: Int) : List<List<Role>> {
    }
}