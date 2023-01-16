package mafia

import mafia.day.DayCreator
import mafia.decks.*
import mafia.decks.classic.*
import mafia.decks.enams.DeckTypes
import mafia.day.DayService
import mafia.day.DayTypes
import mafia.day.defaultDay.DefaultDayService
import mafia.decks.DeckService

class Mafia(deckType: DeckTypes, dayType: DayTypes): RunnableService {
    private var deckService: DeckService = ClassicDeckService()
    private var dayService: DayService = DefaultDayService()

    init {
        deckService = DeckCreator.createDeck(deckType)
        dayService = DayCreator.createDay(dayType)
    }

    override fun run() {
        dayService.configureDaySetting()
        deckService.configureDeckSettings()

        giveRoles()
        while (checkRules()) {
            dayService.runDay()
            if (checkRules()) break
            deckService.runNight()
        }

        determineWinner()
    }
}