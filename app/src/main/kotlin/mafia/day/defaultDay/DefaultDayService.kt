package mafia.day.defaultDay

import mafia.day.DayService

class DefaultDayService: DayService {
    override val dayRunner = DefaultDayRunner()
    override val dayConfigurator = DefaultDayConfigurator()

    override fun runDay() {

    }

    override fun configureDaySetting() {

    }
}