package mafia.decks.urban

import mafia.decks.DeckService

class UrbanDeckService: DeckService {
    override val nightRunner = UrbanNightRunner()
    override val deckConfigurator = UrbanConfiguratorService()

    override fun configureDeckSettings() {
        deckConfigurator.configure()
    }

    override fun runNight() {
        nightRunner.run()
    }
}