package mafia.decks.classic

import exceptions.DataStateException
import mafia.decks.enams.Teams
import mafia.decks.interfaces.RuleChecker
import mafia.models.DeckSettings

class ClassicRuleChecker: RuleChecker {
    override fun checkRules(): Boolean {
        // true - continue
        // false - stop
        val red = DeckSettings.teams[Teams.RED]
            ?: throw DataStateException("Deck settings aren't configured")
        val black = DeckSettings.teams[Teams.BLACK]
            ?: throw DataStateException("Deck settings aren't configured")

        if (black == 0) return false
        if (black >= red) return false
        return true
    }
}