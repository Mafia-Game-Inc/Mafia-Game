package game.mafia

import game.mafia.roles.Roles
import game.mafia.users.Player
import game.mafia.users.Teams
import game.mafia.users.UserState
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.fail

internal class MafiaTest {

    @Test
    fun testKickPlayerByPlayer() {
        val mafia = Mafia("MafiaExample")
        val testPlayer = Player("PlayerExample")

        mafia.players.add(testPlayer)

        mafia.kickPlayer(testPlayer)

        val isKicked = mafia.players.find { it.id == testPlayer.id }

        assertNull(isKicked, "Player hasn't been kicked\n")
        if (
            testPlayer.isHost ||
            testPlayer.position != 0u ||
            testPlayer.role != Roles.NONE ||
            testPlayer.team != Teams.NONE ||
            testPlayer.state != UserState.NOT_IN_GAME
        ) {
            fail("Kicked player didn't return to default state\n")
        }
    }

    @Test
    fun testKickPlayerByPos() {
    }
}