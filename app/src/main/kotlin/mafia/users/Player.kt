package mafia.users

import game.exceptions.InvalidInputArgumentException
import exceptions.InvalidStateException
import mafia.decks.enams.Roles
import mafia.decks.enams.Teams
import kotlin.random.Random
import kotlin.random.nextUInt

class Player {
    val id: UInt = Random.nextUInt()
    var position: Int = 0
    var isHost: Boolean = false
    var isVoted: Boolean = false
    var role: Roles = Roles.NONE
    var team: Teams = Teams.NONE
    var state: PlayerState = PlayerState.NOT_IN_GAME
        private set

    fun vote (playerPos: Int): Boolean {
        if (state != PlayerState.ALIVE) {
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
        if (state != PlayerState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        println("player number $position is saying...")
//        delay(TimeUnit.SECONDS.toMillis(time.toLong()))
    }

    fun shoutOut () {//suspend
        if (state != PlayerState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        println("player number $position is shouting out...")
//        delay(TimeUnit.SECONDS.toMillis(SHOUT_OUT_TIME.toLong()))
    }

    fun expose(alivePlayersPos: MutableList<Int>): Int { // alivePlayers should be set<pos, Player>
        if (state != PlayerState.ALIVE) {
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

    fun chooseFrom(options: List<Int>): Int {
        if (state != PlayerState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        println("player number $position is choosing...")
        println("choose option from below or enter 0")
        println(options)

        val chosenOption = readln().toInt()
        if (chosenOption == 0) return 0

        //here program have to force user to enter correct input
        //don't throw an error
        if (!options.contains(chosenOption)) {
            throw InvalidInputArgumentException("Invalid chosen position: no such position available")
        }

        return chosenOption
    }


    fun toKilledState() {
        state = PlayerState.KILLED
    }
}