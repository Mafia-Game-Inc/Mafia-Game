package mafia

import mafia.decks.*
import mafia.decks.classic.*
import mafia.decks.enams.DeckTypes
import mafia.day.*
import mafia.day.defaultDay.*
import mafia.models.*
import mafia.users.*
import view.View

class Mafia(
    deckType: DeckTypes = DeckTypes.CLASSIC,
    dayType: DayTypes = DayTypes.DEFAULT,
    playersAmount: Int = 10
) : RunnableService {
    private var deckService: DeckService = ClassicDeckService()
    private var dayService: DayService = DefaultDayService()

    init {
        deckService = DeckCreator.createDeck(deckType)
        dayService = DayCreator.createDay(dayType)
        DeckSettings.playersAmount = playersAmount
    }

    fun addPlayer(user: User) {
        Lobby.addUser(user)
    }

    override fun run() {
        dayService.configureDaySetting()
        deckService.configureDeckSettings()

        giveRoles()
        while (checkRules()) {
            dayService.runDay()
            if (!checkRules()) break
            deckService.runNight()
        }

        val winner = determineWinner()
        View.sendMessage("${winner.name} team won!")
    }
}