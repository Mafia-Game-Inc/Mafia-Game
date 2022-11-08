package game.mafia.users

import game.mafia.Mafia
import game.mafia.roles.Roles

import kotlin.random.Random
import kotlin.random.nextUInt

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

open class Player(var nickname: String) {
    val id: UInt = Random.nextUInt()
    var isHost: Boolean = false
    var position: UInt = 0u
    var role: Roles = Roles.NONE
    var team: Teams = Teams.NONE
    var state: UserState = UserState.NOT_IN_GAME

    fun joinGame (gameId: UInt, games: MutableList<Mafia>) {
        //input validation
        //games[gameId].addPlayer(this)

        games.find { it.gameId == gameId }?.addPlayer(this)

        println("player $id joined lobby $gameId")
    }

    fun createGame (lobbyName: String, games: MutableList<Mafia>) {
        val game = Mafia(lobbyName)

        this.isHost = true
        game.addPlayer(this)
        games.add(game)

        println("player $id joined lobby $lobbyName")
    }


    fun vote (playerPos: UInt): Boolean {
        if (playerPos == this.position) return false

        println("vote for $playerPos? (false/true)")
        val voteChoice = readln().toBoolean()

        println("player number $position is voted")

        return voteChoice
    }

    fun say (time: UInt) { //suspend
        println("player number $position is saying...")
//        delay(TimeUnit.SECONDS.toMillis(time.toLong()))
    }

    fun shoutOut () {//suspend
        println("player number $position is shouting out...")
//        delay(TimeUnit.SECONDS.toMillis(SHOUT_OUT_TIME.toLong()))
    }

    fun expose (players: MutableList<Player>): Player? { // alivePlayers should be hashmap<pos, Player>
        println("player number $position is exposing...")

        println("choose player from: $players")// should print only positions

        val chosenPlayerPos = readln().toUInt()

        return players.find { it.position == chosenPlayerPos }
    }


    fun toDefaultState () {
        this.isHost = false
        this.position = 0u
        this.role = Roles.NONE
        this.team = Teams.NONE
        this.state = UserState.NOT_IN_GAME
    }
}