package game.mafia.old.users

import game.exceptions.*
import game.mafia.old.Mafia
import game.mafia.old.roles.Roles

import kotlin.random.Random
import kotlin.random.nextUInt

/*
* add time for all commands
* soo player will have limited time
*/

open class Player(var nickname: String) {
    val id: UInt = Random.nextUInt()
    var position: Int = 0
    var isHost: Boolean = false
    var isVoted: Boolean = false
    var role: Roles = Roles.NONE
    var team: Teams = Teams.NONE
    var state: UserState = UserState.NOT_IN_GAME
        private set

    //переместить метод в контроллер
    fun joinGame (gameId: UInt, games: MutableList<Mafia>) {
        if (state != UserState.NOT_IN_GAME) {
            throw InvalidStateException("Invalid user state: user is already in other game")
        }

        val currGame = games.find { it.gameId == gameId }
            ?: throw InvalidInputArgumentException(
                "Invalid gameId argument: Game with such id doesn't exist"
            )
        currGame.addPlayer(this)

        println("player $id joined lobby ${currGame.gameName}")
    }
    //переместить метод в контроллер
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


    fun vote (playerPos: Int): Boolean {
        if (state != UserState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        println("player number $position is voting...")

        if (playerPos == position) {
            throw InvalidInputArgumentException("Player can't for itself")
        }

        println("vote for $playerPos? (false/true)")
        val voteChoice = readln().toBoolean()
        isVoted = voteChoice

        return voteChoice
    }

    fun say (time: UInt) { //suspend
        if (state != UserState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        println("player number $position is saying...")
//        delay(TimeUnit.SECONDS.toMillis(time.toLong()))
    }

    fun shoutOut () {//suspend
        if (state != UserState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        println("player number $position is shouting out...")
//        delay(TimeUnit.SECONDS.toMillis(SHOUT_OUT_TIME.toLong()))
    }

    fun expose(alivePlayersPos: MutableList<Int>): Int { // alivePlayers should be set<pos, Player>
        if (state != UserState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        println("player number $position is exposing...")
        println("choose player from below or enter 0")
        println(alivePlayersPos)

        val chosenPlayerPos = readln().toInt()
        if (chosenPlayerPos == 0) return 0

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

    //переместить метод в контроллер
    fun choosePosition(availablePositions: List<Int>) {
        if (state != Use)

        println("choose one from below")
        println(availablePositions)

        val chosenPos = readln().toInt()

        //here program have to force user to enter correct input
        //don't throw an error
        if (!availablePositions.contains(chosenPos)) {
            throw IllegalArgumentException("No such choice")
        }

        this.position = chosenPos
    }


    //переместить метод в контроллер
    fun changeNickname() {}

    fun toDefaultState() {
        position = 0
        isHost = false
        isVoted = false
        role = Roles.NONE
        team = Teams.NONE
        state = UserState.NOT_IN_GAME
    }

    fun toKilledState() {
        isVoted = false
        state = UserState.KILLED
    }

    fun toSpectatorState() {
        state = UserState.SPECTATOR
    }

    fun toAliveState() {
        state = UserState.ALIVE
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