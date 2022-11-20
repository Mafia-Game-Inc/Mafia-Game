package game.mafia

import game.mafia.users.Player
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MafiaTest {

    @Test
    fun testKickPlayerByPlayer() {
        val mafia = Mafia("MafiaExample")
        val testPlayer = Player("PlayerExample")

        mafia.players.add(testPlayer)

        mafia.kickPlayer(testPlayer)

        val realPlayer = mafia.players.find { it.id == testPlayer.id }
        testPlayer.equals()
    }

    @Test
    fun testKickPlayerByPos() {
    }
}