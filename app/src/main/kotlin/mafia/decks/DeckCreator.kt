package mafia.decks

import mafia.decks.classic.ClassicDeckService
import mafia.decks.enams.DeckTypes
import mafia.decks.interfaces.DeckRunnerService

object DeckCreator {
    fun createDeck(deckType: DeckTypes): DeckRunnerService {
        when(deckType) {
            DeckTypes.CLASSIC -> return ClassicDeckService()
        }
    }
}