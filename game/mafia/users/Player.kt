package game.mafia.users

import game.mafia.roles.Roles

open class Player(
    var position: Int,
    var id: Int,
    var role: Roles,
    var state: UserState
    )
{

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