package game.mafia.users

import game.exceptions.*
import game.mafia.Mafia
import game.mafia.roles.Roles

import kotlin.random.Random
import kotlin.random.nextUInt

/*
* add time for all commands
* soo player will have limited time
*/

open class Player(var nickname: String) {
    val id: UInt = Random.nextUInt()
    var position: UInt = 0u
    var isHost: Boolean = false
    var isVoted: Boolean = false
    var role: Roles = Roles.NONE
    var team: Teams = Teams.NONE
    var state: UserState = UserState.NOT_IN_GAME

    fun joinGame (gameId: UInt, games: MutableList<Mafia>) {
        if (this.state != UserState.NOT_IN_GAME) {
            throw InvalidStateException("Invalid user state: user is already in other game")
        }

        val currGame = games.find { it.gameId == gameId }
            ?: throw InvalidInputArgumentException(
                "Invalid gameId argument: Game with such id doesn't exist"
            )
        currGame.addPlayer(this)

        println("player $id joined lobby ${currGame.gameName}")
    }

    fun createGame (gameName: String, games: MutableList<Mafia>): Mafia {
        if (this.state != UserState.NOT_IN_GAME) {
            throw InvalidStateException("Invalid user state: user is already in other game")
        }

        if (games.find { it.gameName == gameName } != null) {
            throw InvalidInputArgumentException("Invalid game name: game with such name already exists")
        }

        val game = Mafia(gameName)

        this.isHost = true
        game.addPlayer(this)
        games.add(game)

        println("player $id joined lobby $gameName")

        return game
    }


    fun vote (playerPos: UInt): Boolean {
        println("player number $position is voting...")

        if (playerPos == this.position) {
            throw InvalidInputArgumentException("Player can't for itself")
        }

        println("vote for $playerPos? (false/true)")
        val voteChoice = readln().toBoolean()
        isVoted = voteChoice

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

    fun expose(alivePlayersPos: MutableList<UInt>): UInt { // alivePlayers should be set<pos, Player>
        println("player number $position is exposing...")
        println("choose player from below or enter 0")
        println(alivePlayersPos)

        val chosenPlayerPos = readln().toUInt()
        if (chosenPlayerPos == 0u) return 0u

        //here program have to force user to enter correct input
        //don't throw an error
        if (chosenPlayerPos == this.position) {
            throw InvalidInputArgumentException("Invalid chosen position: player can't expose itself")
        }
        if (!alivePlayersPos.contains(chosenPlayerPos)) {
            throw InvalidInputArgumentException("Invalid chosen position: no such position available")
        }

        return chosenPlayerPos
    }

    fun choosePosition(availablePositions: List<UInt>) {
        println("choose one from below")
        println(availablePositions)

        val chosenPos = readln().toUInt()

        //here program have to force user to enter correct input
        //don't throw an error
        if (!availablePositions.contains(chosenPos)) {
            throw IllegalArgumentException("No such choice")
        }

        this.position = chosenPos
    }


    fun toDefaultState () {
        this.isHost = false
        this.position = 0u
        this.role = Roles.NONE
        this.team = Teams.NONE
        this.state = UserState.NOT_IN_GAME
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Player(nickname='$nickname', id=$id, isHost=$isHost, position=$position, role=$role, team=$team, state=$state)"
    }
}