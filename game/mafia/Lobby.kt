package game.mafia

import game.mafia.users.Host
import game.mafia.users.Player

class Lobby (
    var lobbyId: Int,
    var lobbyName: String,
    var moderator: Host,
    var players: MutableList<Player>
    )
{

}