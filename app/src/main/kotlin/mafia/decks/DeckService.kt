package mafia.decks

import mafia.ConfiguratorService
import mafia.RunnableService

interface DeckService {
    val nightRunner: RunnableService
    val deckConfigurator: ConfiguratorService

    fun configureDeckSettings()

    fun runNight()
}