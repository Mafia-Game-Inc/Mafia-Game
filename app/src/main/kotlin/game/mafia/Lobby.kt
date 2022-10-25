package game.mafia

import game.mafia.users.Host
import game.mafia.users.Player

class Lobby(
    lobbyName: String,
    var moderator: Host,
) {
    // подумать над тем как лучше ситуацию с lobbyName зарефакторить
    // такая же проблема с nickname в Player
    var lobbyName: String = ""
        set(value) {
            //lobbyName validation
            field = value
        }
    var players: MutableList<Player> = mutableListOf()

    init {
        this.lobbyName = lobbyName
    }

    fun addPlayer(player: Player) {
        this.players.add(player)
    }
}