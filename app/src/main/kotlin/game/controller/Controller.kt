package game.controller

import game.mafia.users.Player
import game.mafia.roles.Roles

class Controller {
    private var playersMap: <int, Player> = players.associateBy { it.position }
    private var nightActions: NightActions = NightActions()
    private lateinit var currentPhase: GamePhase
    private lateinit var voteTracker: VoteTracker

    fun handleInput(player: Player, input: String) {
        when (currentPhase) {
            GamePhase.NIGHT -> handleNightInput(player, input)
            GamePhase.DAY -> handleDayInput(player, input)
            GamePhase.END -> handleEndInput(player, input)
        }
    }

    // methods for handling input during specific phases
    private fun handleNightInput(player: Player, input: String) {
        // process night action input and update game state accordingly
        nightActions.processAction(player, input)
    }

    private fun handleDayInput(player: Player, input: String) {
        // process vote input and update game state accordingly
        voteTracker.processVote(player, input)
    }

    private fun handleEndInput(player: Player, input: String) {

    }

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
        // code for lynching a player
    }
}
class NightActions {
    private var actions: MutableMap<Player, Action> = mutableMapOf()

    // method for processing night action input
    fun processAction(player: Player, input: String) {
        val action = parseInput(player, input)
        if (action != null) {
            actions[player] = action
        }
    }

    // method for executing night actions
    fun executeActions() {
        actions.forEach { (player, action) ->
            when (action) {
                is KillAction -> killPlayer(action.targetPlayer)
                is InvestigateAction -> investigatePlayer(action.targetPlayer)
                is SearchSheriffAction -> searchSheriff(action.targetPlayer)
                is InvestigateMafiaAction -> investigateMafia(action.targetPlayer)
            }
        }
        actions.clear() // clear actions after they have been executed
    }

    // helper method for parsing input and returning an action object
    private fun parseInput(player: Player, input: String): Action? {
        val inputComponents = input.split(" ")
        val actionType = inputComponents[0]
        val targetPlayer = inputComponents[1]

        return when (actionType) {
            "kill" -> KillAction(targetPlayer)
            "investigate" -> InvestigateAction(targetPlayer)
            "search_sheriff" -> {
                if (player.role == Roles.GODFATHER) {
                    SearchSheriffAction(targetPlayer)
                } else {
                    displayMessage("Invalid action for this player")
                    null
                }
            }
            "investigate_mafia" -> {
                if (player.role == Roles.SHERIFF) {
                    InvestigateMafiaAction(targetPlayer)
                } else {
                    displayMessage("Invalid action for this player")
                    null
                }
            }
            else -> null
        }
    }
