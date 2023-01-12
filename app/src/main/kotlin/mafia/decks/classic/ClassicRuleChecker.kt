package mafia.decks.classic

import mafia.decks.interfaces.RuleChecker
import mafia.models.DeckSettings

class ClassicRuleChecker: RuleChecker {
    override fun checkRules(): Boolean {
        // true - continue
        // false - stop

        val red = DeckSettings.redPlayers
        val black = DeckSettings.blackPlayers

        if (black == 0) return false
        if (black >= red) return false
        return true
    }
}