package mafia

import mafia.decks.*
import mafia.decks.classic.*
import mafia.decks.enams.DeckTypes
import mafia.decks.interfaces.DeckRunnerService

class Mafia(deckType: DeckTypes): RunnableService {
    private var deckService: DeckRunnerService = ClassicDeckService()


    init {
        deckService = DeckCreator.createDeck(deckType)
    }

    override fun run() {
        deckService.run()
    }
}