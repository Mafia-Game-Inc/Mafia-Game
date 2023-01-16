package mafia.decks.classic

import mafia.decks.DeckService

class ClassicDeckService: DeckService {
    override val nightRunner = ClassicNightRunner()
    override val deckConfigurator = ClassicConfiguratorService()

    override fun configureDeckSettings() {
        TODO("Not yet implemented")
    }

    override fun runNight() {
        TODO("Not yet implemented")
    }
}