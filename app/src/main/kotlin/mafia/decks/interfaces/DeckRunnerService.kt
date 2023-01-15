package mafia.decks.interfaces

import mafia.RunnableService

interface DeckRunnerService: RunnableService {
    val dayRunner: RunnableService
    val nightRunner: RunnableService
    val configurator: ConfiguratorService
}