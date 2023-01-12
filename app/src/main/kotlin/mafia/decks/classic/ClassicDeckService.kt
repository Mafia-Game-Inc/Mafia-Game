package mafia.decks.classic

import mafia.decks.interfaces.DeckRunnerService

class ClassicDeckService: DeckRunnerService {
    override val dayRunner = ClassicDayRunner()
    override val nightRunner = ClassicNightRunner()
    override val ruleChecker = ClassicRuleChecker()
    override val configurator = ClassicConfiguratorService()

    override fun run() {

    }
}