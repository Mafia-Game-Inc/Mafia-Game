package mafia.decks

import mafia.decks.classic.ClassicDeckService
import mafia.decks.enams.DeckTypes

object DeckCreator {
    fun createDeck(deckType: DeckTypes): DeckService {
        when(deckType) {
            DeckTypes.CLASSIC -> return ClassicDeckService()
        }
    }
}