package mafia.day.defaultDay

import mafia.ConfiguratorService
import mafia.models.DaySettings

class DefaultDayConfigurator: ConfiguratorService {
    private val defaultTimeForSpeech = 60u
    private val defaultTimeForDefence = 30u

    override fun configure() {
        DaySettings.timeForSpeech = defaultTimeForSpeech
        DaySettings.timeForDefence = defaultTimeForDefence
    }
}