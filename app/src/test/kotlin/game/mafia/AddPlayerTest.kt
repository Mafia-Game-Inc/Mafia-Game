package game.mafia

import game.mafia.roles.Roles
import game.mafia.users.Player
import game.mafia.users.UserState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AddPlayerTest {

    @Test
    fun addPlayer() {

        val mafia = Mafia("testExample")
        val testPlayersState = mutableListOf<Player>()
        val testAlive = 9
        val testSpectators = 3
        val playersCount = testAlive + testSpectators

        for (i in 1..playersCount) {
            val player = Player("TestPlayer #$i")

            mafia.add(player)  // Не шарю, как добавить в список. Сделал так, как было у Саши М.

            if (i > 9) {
                player.state = UserState.SPECTATOR
            } else {
                player.state = UserState.ALIVE
            }

            testPlayersState.add(player) // Не шарю, как добавить в список. Сделал так, как было у Саши М.

            assertEquals(
                null,
                player,
                "Player object is null\n"
            )
        }

        val realAlive = mafia.players.count { it.state = UserState.ALIVE }
        val realSpecs = mafia.players.count { it.state = UserState.SPECTATOR }

        assertEquals(
            realAlive,
            testAlive,
            "Spectator state was incorectly assigned\n"
        )

        assertEquals(
            realAlive,
            testAlive,
            "Alive state was incorectly assigned\n"
        )

    }
}