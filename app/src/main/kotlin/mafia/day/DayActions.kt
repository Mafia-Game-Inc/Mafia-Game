package mafia.day

import mafia.decks.kill
import mafia.users.Player

fun hangPlayer(player: Player) {
    kill(player)
    println("city voted to kill player at ${player.position} place today")
}