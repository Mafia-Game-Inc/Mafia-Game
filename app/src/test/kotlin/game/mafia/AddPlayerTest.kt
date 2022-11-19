package game.mafia

import game.mafia.roles.Roles
import game.mafia.users.Player
import game.mafia.users.UserState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AddPlayerTest {

    @Test
    fun addPlayer() {
        val player = Player("testExample")
        this.players.size = 10

        mafia.addPlayer(player)

        assertTrue(player.state != UserState.SPECTATOR,
            () → "Player state incorrect assigned"
        )

        assertNull(
            player.position, "Player position incorrect assigned"
        )

    }
}