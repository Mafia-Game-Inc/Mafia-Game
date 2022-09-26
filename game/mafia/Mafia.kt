package game.mafia

import game.mafia.users.Player

import game.mafia.roles.classic.*

class Mafia {
    // можно выделить в одельный клас лобби
    var lobbyId: Int = -1
    var lobbyName: String = "NONE"

    var players: MutableList<Player> = mutableListOf()

    fun gameLoop () {
        // game...
    }

    fun giveRoles () {

        // скорее здесь нужен простой фабричный метод

        // решить проблему с тем что не понятно какую фабрику создавать и как именно
        // паттрен работает в итоге с клиентом

        // скорее всего даже этой фабрики не надо
        // а какую-то функцию которая относительно конфигурации и кол. игроков будет
        // выдавать массив(или что-то другое) с ролями

        // правда нужно подумать ка потом рандомно выдавать роли
        // тип если их сначала копировать а потом давать игрокам, звучит так себе
    }

    fun createPreSet (preSet: PreSet, amountOfplayers: Int) : List<List<Role>> {
        // как-то отностельно пресета генерить все нужные инстансы
    }
}