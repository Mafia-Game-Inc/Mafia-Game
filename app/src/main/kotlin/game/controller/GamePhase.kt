package game.controller

enum class GamePhase {
    NIGHT, DAY, END;

    val isNight: Boolean
        get() = this == NIGHT

    val isDay: Boolean
        get() = this == DAY

    val isEnd: Boolean
        get() = this == END

    fun nextPhase() : GamePhase {
        return when (this) {
            NIGHT -> DAY
            DAY -> NIGHT
            END -> END
        }
    }
}