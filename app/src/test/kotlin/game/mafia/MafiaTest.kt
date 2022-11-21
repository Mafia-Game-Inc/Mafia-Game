package game.mafia

import game.mafia.roles.Roles
import game.mafia.roles.kill
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
    }

    @Test
    fun testKill () {
        val player = Player ("test")
        player.state = UserState.ALIVE
        kill(player)
        assertTrue(player.state == UserState.KILLED, "Player isn't dead")
    }

    @Test
    fun testJoinLobby() {
        val mafia = Mafia("test")
        val id = mafia.gameId
        val empList = mutableListOf<Mafia>()
        val player = Player("testPlayer")
        empList.add(mafia)

        player.joinGame(id, empList)
        assertEquals(player.id, mafia.players.first().id, "Player hasn't joined")
    }

}