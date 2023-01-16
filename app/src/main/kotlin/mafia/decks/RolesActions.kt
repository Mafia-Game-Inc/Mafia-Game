package mafia.decks

import mafia.models.DeckSettings
import mafia.users.Player

fun kill(player: Player) {
    DeckSettings.removePlayer(player)
    player.toKilledState()
}