package game.controller

import game.mafia.users.Player
import game.mafia.roles.Roles
import game.mafia.users.UserStates

class Controller {
    private var playersMap: Map<Int, Player> = players.associateBy { it.position }
    private var nightActions: NightActions = NightActions()
    private var currentPhase: GamePhase = GamePhase.DAY
    private var voteTracker: VoteTracker = VoteTracker()

    fun handleInput(player: Player, input: String) {
        when (currentPhase) {
            GamePhase.NIGHT -> handleNightInput(player, input)
            GamePhase.DAY -> handleDayInput(player, input)
//          GamePhase.END -> handleEndInput(player, input)
        }
    }
    private fun handleNightInput(player: Player, input: String) {
        nightActions.processAction(player, input)
        nightActions.executeActions()
        currentPhase = currentPhase.nextPhase()
    }

    private fun handleDayInput(player: Player, input: String) {
        voteTracker.processVote(player, input)
        voteTracker.judge(voteTracker.votes)
        currentPhase = currentPhase.nextPhase()
    }

//    private fun handleEndInput(player: Player, input: String) {
//
//    }
    private fun checkForWin() {
    }
}
class VoteTracker {
    var votes: MutableMap<Player, Player> = mutableMapOf()
    fun processVote(voter: Player, input: String) {
        val vote: Player? = parseInput(voter, input)
        if (vote != null) {
            votes[voter] = vote
        }
    }
    fun judge(votes: Map<Player, Player>) {
        val voteCount = mutableMapOf<Player, Int>()
        for (vote in votes.values) {
            if (voteCount.containsKey(vote)) {
                voteCount[vote] = voteCount[vote]!! + 1
            } else {
                voteCount[vote] = 1
            }
        }
        var maxVotes = 0
        var maxPlayer: Player? = null
        for (entry in voteCount) {
            if (entry.value > maxVotes) {
                maxVotes = entry.value
                maxPlayer = entry.key
            }
        }
        if (maxPlayer != null) {
            lynchPlayer(maxPlayer)
        }
        else println("No one was killed")
    }
    private fun parseInput(voter:Player, input: String) : Player? {
        TODO()
    }
    private fun lynchPlayer(targetPlayer: Player) {
        targetPlayer.state = UserStates.KILLED
        votes.clear()
    }
}

class NightActions {
    private var actions: MutableMap<Player, Action> = mutableMapOf()
    private var votingMap: MutableMap<Player, Player> = mutableMapOf()
    private var playersMap: Map<Int, Player> = players.associateBy { it.position }
    fun processAction(player: Player, input: String) {
        val action = parseInput(player, input)
        if (action != null) {
            actions[player] = action
        }
    }

    fun executeActions() {
        actions.forEach { (player, action) ->
            action.executeAction()
        }
        actions.clear()
    }

    private fun parseInput(player: Player, input: String): Action? {
        val inputComponents = input.split(" ")
        val actionType = inputComponents[0]
        val targetPlayer = playersMap[inputComponents[1].toInt()]
        if(actionType != null) {
            return when (actionType) {
                "kill" -> targetPlayer?.let { KillAction(it) }
                "search_sheriff" -> {
                    if (player.role == Roles.GODFATHER) {
                        targetPlayer?.let { SearchSheriffAction(it) }
                    } else {
                        println("Invalid action for this player")
                        null
                    }
                }

                "search_mafia" -> {
                    if (player.role == Roles.SHERIFF) {
                        targetPlayer?.let { SearchMafiaAction(it) }
                    } else {
                        println("Invalid action for this player")
                        null
                    }
                }
                else -> null
            }
        }
        else return null
    }
}