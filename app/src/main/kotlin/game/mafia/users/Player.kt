package game.mafia.users

import game.mafia.roles.Roles
import kotlin.random.Random
import kotlin.random.nextUInt

open class Player {
    var nickname: String = ""
        set(value) {
            //nickname validation
            field = value
        }
    protected var id: UInt = Random.nextUInt()
    var position: Int = -1
    var role: Roles = Roles.NONE
    var state: UserState = UserState.NOT_IN_GAME

    constructor(nickname: String) {
        this.nickname = nickname
    }

    constructor(clone: Player){
        nickname = clone.nickname
        id = clone.id
        position = clone.position
        role = clone.role
        state = clone.state
    }

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