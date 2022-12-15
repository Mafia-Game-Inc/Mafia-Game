package game.mafia

import game.exceptions.*
import game.mafia.old.roles.*
import game.mafia.old.users.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.fail

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
    }

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
    }

    @Test
    fun testAddPlayer() {
        val mafia = Mafia("testMafia")

        //testing if method correctly adds player
        val ex1 = Player("ex1")
        val testPos = 4u

        mafia.addPlayer(ex1, testPos)

        assertTrue(
            ex1.state == UserState.ALIVE,
            "Method didn't change state to ALIVE\n"
        )
        assertTrue(
            ex1.position == testPos,
            "Method didn't change position\n"
        )
        assertTrue(
            mafia.players.contains(ex1),
            "Method didn't add player\n"
        )

        //testing if method correctly adds player if lobby is full
        val ex2 = Player("ex2")

        mafia.players.clear()
        for (i in 1..9) {
            mafia.players.add(Player("$i"))
        }
        mafia.addPlayer(ex2)

        assertTrue(
            ex2.state == UserState.SPECTATOR,
            "Method didn't change state to SPECTATOR\n"
        )

        //testing if method throw error
        val ex3 = Player("ex3")

        mafia.players.clear()
        mafia.addPlayer(ex3, 6u)

        val exception = assertThrows(InvalidInputArgumentException::class.java) {
            mafia.addPlayer(ex3)
        }
        assertEquals(
            exception.message,
            "Invalid player object: that player is already in this lobby"
        )
    }
}