package game.mafia.old.roles

import game.mafia.old.users.Player
import game.mafia.old.users.Teams

abstract class Preset {
    abstract var playersAmount: Int
    abstract var redPlayers: Int
    abstract var blackPlayers: Int
    abstract var activePlayersAmount: Int
    abstract var activeRoles: List<RoleData>

    abstract fun configure()

    abstract fun runNight(players: MutableList<Player>)

    fun removePlayer(player: Player) {
        if (player.team == Teams.RED) { redPlayers-- }
        else { blackPlayers-- }
    }
}