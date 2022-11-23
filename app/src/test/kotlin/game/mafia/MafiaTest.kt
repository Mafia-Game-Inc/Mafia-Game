package game.mafia

<<<<<<< HEAD
import game.mafia.roles.Roles
import game.mafia.users.Player
import game.mafia.users.UserState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MafiaTest {
    @Test
    fun testGiveRoles() {
        val mafia = Mafia("testExample")
        val testPlayers = mutableListOf<Player>()
        val sheriffAmount = 1
        val godFatherAmount = 1
        val mafiaAmount = 2

        for (i in 1..9) {
            val player = Player("$i")
            player.position = i.toUInt()
            player.state = UserState.ALIVE

            testPlayers.add(player)
            mafia.players.add(player)
        }

        mafia.giveRoles()

        val realSheriffAmount = mafia.players.count { it.role == Roles.SHERIFF }
        val realGodFatherAmount = mafia.players.count { it.role == Roles.GODFATHER }
        val realMafiaAmount = mafia.players.count { it.role == Roles.MAFIA }

        assertEquals(
            sheriffAmount,
            realSheriffAmount,
            "Sheriff role incorrectly assigned:\n"
        )

        assertEquals(
            godFatherAmount,
            realGodFatherAmount,
            "GodFather role incorrectly assigned:\n"
        )

        assertEquals(
            mafiaAmount,
            realMafiaAmount,
            "Mafia role incorrectly assigned:\n"
        )
=======
import game.exceptions.*
import game.mafia.roles.*
import game.mafia.users.*

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
        ) fail("Kicked player didn't return to default state\n")

        var isExceptionThrown = false

        mafia.players.clear()
        try {
            mafia.kickPlayer(testPlayer)
        } catch (e: Exception) {
            isExceptionThrown = true
            assertTrue(e is InvalidInputArgumentException,
                "Method must trow InvalidInputArgumentException")
            assertEquals(e.message, "No such player in lobby",
                "Method exception message must be - No such player in lobby")
        }
        if (!isExceptionThrown) {
            fail("Method must throw InvalidInputArgumentException")
        }
    }

    @Test
    fun testKickPlayerByPos() {
        val mafia = Mafia("testMafia")
        val testPlayer = Player("testPlayer")
        testPlayer.position = 5u

        mafia.players.add(testPlayer)
        mafia.kickPlayer(testPlayer.position)

        val isKicked = mafia.players.find { it.id == testPlayer.id }
        assertNull(isKicked, "Player hasn't been kicked\n")

        testPlayer.position = 5u
        mafia.players.clear()
        try {
            mafia.kickPlayer(testPlayer.position)
        } catch (e: Exception) {
            assertTrue(e is InvalidInputArgumentException,
                "Method must trow InvalidInputArgumentException")
            assertEquals(e.message, "No such player in lobby",
                "Method exception message must be - No such player in lobby")
        }

        var isExceptionThrown = false

        testPlayer.position = 12u
        try {
            mafia.kickPlayer(testPlayer.position)
        } catch (e: Exception) {
            isExceptionThrown = true
            assertTrue(e is InvalidInputArgumentException,
                "Method must trow InvalidInputArgumentException")
            assertEquals(e.message, "Position out is bounds",
                "Method exception message must be - Position out is bounds")
        }

        if (!isExceptionThrown) {
            fail("Method must throw InvalidInputArgumentException")
        }
>>>>>>> writing_testKickPlayer
    }
}