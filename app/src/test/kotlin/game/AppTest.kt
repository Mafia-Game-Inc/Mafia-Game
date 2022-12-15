package game

import game.mafia.Mafia
import game.mafia.old.users.Player

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class AppTest {
    @Test
    fun endToEndTest() {
        val games = mutableListOf<Mafia>()
        val host = Player("Host")

        val testGame = host.createGame("Test_Game", games)

        assertTrue(
            games.contains(testGame),
            "Error in createGame: games doesn't contain created game"
        )

        var i = 'a'
        val testPlayers = MutableList(8) { Player("${i++}") }

        for (player in testPlayers) {
            player.joinGame(testGame.gameId, games)
        }
        testPlayers.add(host)

        assertEquals(
            testGame.players,
            testPlayers,
            "Error in join game: player haven't been added to game"
        )

        testGame.startGame()
    }
}
