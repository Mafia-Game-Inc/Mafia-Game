package mafia.decks

import mafia.decks.classic.ClassicDeckService
import mafia.decks.enams.DeckTypes
import mafia.decks.urban.UrbanDeckService

object DeckCreator {
    fun createDeck(deckType: DeckTypes): DeckService {
        return when(deckType) {
            DeckTypes.CLASSIC -> ClassicDeckService()
            DeckTypes.URBAN -> UrbanDeckService()
        }
    }
}