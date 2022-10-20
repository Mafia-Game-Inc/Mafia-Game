package game.mafia.users

import game.mafia.roles.Roles
import javax.annotation.meta.TypeQualifierNickname

open class Player(
    var nickname: String,
    var position: Int,
    var id: Int,
    var role: Roles,
    var state: UserState
    )
{

    fun joinGame (gameId: Int) {

        /*
        * input validation
        * "some_collection"[gameId].lobby.addPlayer(this)
        *
        * println("player $id joined lobby $lobbyId")
        * */
    }

    fun createGame (lobbyName: String) {
        println("player $id joined lobby $lobbyName")
    }


    fun vote () {
        println("player number $position is voting...")
    }

    fun say (time: UInt) {
        println("player number $position is saying...")
    }

    fun shoutOut () {
        println("player number $position is shouting out...")
    }

    fun expose () {
        println("player number $position is exposing...")
    }
}