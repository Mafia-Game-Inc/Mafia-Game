package mafia.day

import mafia.decks.kill
import mafia.users.User

fun hangPlayer(player: User) {
    kill(player)
    println("city voted to kill player at ${player.position} place today")
}