package game.controller

import game.mafia.users.Player
import game.mafia.roles.Roles

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
    }

    private fun handleDayInput(player: Player, input: String) {
        voteTracker.processVote(player, input)
    }

//    private fun handleEndInput(player: Player, input: String) {
//
//    }
    private fun checkForWin() {
    }
}
class VoteTracker {
    private var votes: MutableMap<Player, Player> = mutableMapOf()

    fun processVote(voter: Player, input: String) {
        val vote: Player? = parseInput(input)
        if (vote != null) {
            votes[voter] = vote
        }
    }

    fun countVotes() {
        val voteCount = votes.values.groupBy { it }.mapValues { it.value.size }

        //!!!Preferably it shouldn't be "maxBy", value should be returned only if it's max and single
        val mostVotes = voteCount.maxBy { it.value }
        if (mostVotes != null) {
            val targetPlayer = mostVotes.key
            val voteCount = mostVotes.value
            if (voteCount > (votes.size / 2)) {
                lynchPlayer(targetPlayer)
            }
        }
        votes.clear()
    }

    private fun lynchPlayer(targetPlayer: Player) {
    }
}
class NightActions {
    private var actions: MutableMap<Player, Action> = mutableMapOf()
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