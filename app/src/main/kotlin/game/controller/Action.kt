package game.controller

import game.mafia.users.Player

sealed class Action {
    abstract val targetPlayer: Player
}

class KillAction(override val targetPlayer: Player) : Action()
class InvestigateAction(override val targetPlayer: Player) : Action()
class SearchSheriffAction(override val targetPlayer: Player) : Action()
class InvestigateMafiaAction(override val targetPlayer: Player) : Action()
class OccupyNightAction(override val targetPlayer: Player) : Action()
class HealAction(override val targetPlayer: Player) : Action()