package game.mafia.users

import game.mafia.Mafia
import game.mafia.roles.Roles

import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlinx.coroutines.*

/*
* add time for all commands
* soo player will have limited time
*/

/*
* rewrite constant declaration
* according to convention
* https://stackoverflow.com/questions/44038721/constants-in-kotlin-whats-a-recommended-way-to-create-them
* */
const val SHOUT_OUT_TIME = 5

open class Player {
    var nickname: String = ""
        set(value) {
            //nickname validation
            field = value
        }
    private var id: UInt = Random.nextUInt()
    var position: Int = -1
    var role: Roles = Roles.NONE
    var state: UserState = UserState.NOT_IN_GAME

    constructor(nickname: String) {
        this.nickname = nickname
    }

    constructor(clone: Player) {
        nickname = clone.nickname
        id = clone.id
        position = clone.position
        role = clone.role
        state = clone.state
    }

    fun joinGame (gameId: Int, games: MutableList<Mafia>) {
        //input validation
        games[gameId].lobby.addPlayer(this)

        println("player $id joined lobby $gameId")
    }

    fun createGame (lobbyName: String, games: MutableList<Mafia>) {
        games.add(Mafia(lobbyName, this))

        println("player $id joined lobby $lobbyName")
    }


    fun vote (playerPos: Int): Boolean {
        if (playerPos == this.position) return false

        println("vote for $playerPos? (false/true)")
        val voteChoice = readln().toBoolean()

        println("player number $position is voted")

        return voteChoice
    }

    suspend fun say (time: UInt) {
        println("player number $position is saying...")
        delay(TimeUnit.SECONDS.toMillis(time.toLong()))
    }

    suspend fun shoutOut () {
        println("player number $position is shouting out...")
        delay(TimeUnit.SECONDS.toMillis(SHOUT_OUT_TIME.toLong()))
    }

    fun expose (alivePlayers: List<Int>): Int {
        println("player number $position is exposing...")

        println("choose player from: $alivePlayers")

        val chosenPlayer = readln().toInt()
        if (!alivePlayers.contains(chosenPlayer)) println("wrong player!")

        return chosenPlayer
    }
}