package mafia.decks.classic

import mafia.decks.DayRunner
import mafia.decks.interfaces.DeckRunnerService

class ClassicDeckService: DeckRunnerService {
    override val dayRunner = DayRunner()
    override val nightRunner = ClassicNightRunner()
    override val configurator = ClassicConfiguratorService()

    override fun run() {
        configurator.configure()

        while (ruleChecker.checkRules()) {

        }
    }
}