package game.mafia

import game.mafia.users.Host
import game.mafia.users.Player

class Lobby (
    val lobbyName: String,
    var moderator: Host,
    var players: MutableList<Player>
    )
{
    fun addPlayer(player: Player) {}
}