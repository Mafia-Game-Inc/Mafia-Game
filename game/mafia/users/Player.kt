package game.mafia.users

import game.mafia.roles.ActiveRole

open class Player(var position: Int, var id: Int) {


    fun joinLobby (lobbyId: Int) {
        println("player $id joined lobby $lobbyId")
    }

    fun createLobby (lobbyName: String) {
        println("player $id joined lobby $lobbyName")
    }


    fun vote () {
        println("player number $position is voting...")
    }

    fun say () {
        println("player number $position is saying...")
    }

    fun shoutOut () {
        println("player number $position is shouting out...")
    }

    fun expose () {
        println("player number $position is exposing...")
    }
}

// первый вариант прописывать все обычные методы в плеере либо
// прописать отдельный класс типа Base или мирный от которого будут наследоваться
// классы активных ролей

// надо подумать являеться ли плеер и хост частями логики модели
// и нужно ли в самой модельке прописывать эти методы типа создать лобби и тд.