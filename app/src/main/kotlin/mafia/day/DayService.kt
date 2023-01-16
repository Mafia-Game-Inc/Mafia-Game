package mafia.day

import mafia.RunnableService
import mafia.ConfiguratorService

interface DayService {
    val dayRunner: RunnableService
    val dayConfigurator: ConfiguratorService

    fun runDay()

    fun configureDaySetting()
}