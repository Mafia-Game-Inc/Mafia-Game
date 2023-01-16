package mafia.day

import mafia.day.defaultDay.DefaultDayService


object DayCreator {
    fun createDay(dayType: DayTypes): DayService {
        when(dayType) {
            DayTypes.DEFAULT -> return DefaultDayService()
        }
    }
}