package mafia.decks

import mafia.models.DeckSettings
import mafia.users.User

fun kill(user: User) {
    DeckSettings.removePlayer(user)
    user.toKilledState()
}