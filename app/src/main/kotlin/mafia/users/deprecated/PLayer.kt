package mafia.users.deprecated

import exceptions.*
import mafia.decks.enams.*
import view.View

/*class Player(val position: Int): Spectator()  {
    var isVoted: Boolean = false
    var role: Roles = Roles.NONE
        private set
    var team: Teams = Teams.NONE
        private set
    var state: PlayerState = PlayerState.NOT_INITIALIZED
        private set

    fun vote (playerPos: Int): Boolean {
        if (state != PlayerState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }
        if (playerPos == position) {
            throw InvalidInputArgumentException("Player can't for itself")
        }

        View.log("player number $position is voting...")
        View.sendMessage("vote for $playerPos? (false/true)")
        val voteChoice = View.readBoolean()

        isVoted = voteChoice
        return voteChoice
    }

    fun say (time: UInt) { //suspend
        if (state != PlayerState.ALIVE) {
            throw InvalidStateException("Invalid user state: player is not alive")
        }

        View.log("player number $position is saying...")
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

        View.log("player number $position is exposing...")
        alivePlayersPos.add(0)
        View.sendMessage("choose player from below or enter 0\n$alivePlayersPos")


        val chosenPlayerPos = View.readInt(alivePlayersPos)
        if (chosenPlayerPos == 0) return 0

        if (chosenPlayerPos == this.position) {
            throw InvalidInputArgumentException("Invalid chosen position: player can't expose itself")
        }

        return chosenPlayerPos
    }


    fun toAliveState(role: Roles, team: Teams) {
        state = PlayerState.ALIVE
        this.team = team
        this.role = role
    }

    fun toKilledState() {
        state = PlayerState.KILLED
    }
}*/
