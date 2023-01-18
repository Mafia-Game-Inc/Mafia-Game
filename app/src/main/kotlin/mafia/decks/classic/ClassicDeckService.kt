package mafia.decks.classic

import mafia.decks.DeckService

class ClassicDeckService: DeckService {
    override val nightRunner = ClassicNightRunner()
    override val deckConfigurator = ClassicConfiguratorService()

    override fun configureDeckSettings() {
        deckConfigurator.configure()
    }

    override fun runNight() {
        nightRunner.run()
    }
}