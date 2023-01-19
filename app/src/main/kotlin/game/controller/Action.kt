package game.controller

import game.mafia.roles.Roles
import game.mafia.users.Player

sealed class Action {
    abstract val targetPlayer: Player
    abstract fun executeAction ()
}

class KillAction(override val targetPlayer: Player) : Action() {
    override fun executeAction() {
        TODO("Not yet implemented")
    }
}
class SearchSheriffAction(override val targetPlayer: Player) : Action() {
    override fun executeAction() {
        if(targetPlayer.role == Roles.SHERIFF) {
            println("Player ${targetPlayer.position} is sheriff.")
        }
    }
}
class SearchMafiaAction(override val targetPlayer: Player) : Action() {
    override fun executeAction() {
        if(targetPlayer.role == Roles.GODFATHER ||  targetPlayer.role == Roles.MAFIA) {
            println("Player ${targetPlayer.position} is bad news.")
        }
    }
}
class OccupyNightAction(override val targetPlayer: Player) : Action() {
    override fun executeAction() {
        TODO("Not yet implemented")
    }
}

class HealAction(override val targetPlayer: Player) : Action() {
    override fun executeAction() {
        TODO("Not yet implemented")
    }
}