package controller

import mafia.Mafia
import mafia.decks.enams.DeckTypes
import mafia.users.User
import view.View

object Controller {
    private var mafia: Mafia = Mafia()
    private val deckTypes: Map<Int, DeckTypes> = mapOf(
        1 to DeckTypes.CLASSIC,
        2 to DeckTypes.URBAN
    )

    fun play() {
        welcomeWord()
        val chosenDeck = chooseDeckType()
        mafia = Mafia(deckTypes[chosenDeck]!!)
        mafia.run()
    }

    fun addPlayer(user: User) {
        mafia.addPlayer(user)
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