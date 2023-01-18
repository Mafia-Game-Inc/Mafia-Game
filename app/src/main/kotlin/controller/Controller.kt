package controller

import mafia.Mafia
import mafia.decks.enams.DeckTypes
import view.View

object Controller {
    private var mafia: Mafia = Mafia()
    private val deckTypes: Map<Int, DeckTypes> = mapOf()

    fun play() {
        welcomeWord()
        val chosenDeck = chooseDeckType()
        mafia = Mafia(deckTypes[chosenDeck]!!)
        mafia.run()
    }

    private fun welcomeWord() {
        View.sendMessage("Welcome to Mafia game")
    }

    private fun chooseDeckType(): Int {
        View.sendMessage("Choose deck type:")
        View.sendMessage("1. Classic\n2. Urban")
        return View.readInt(deckTypes.keys.toList())
    }
}