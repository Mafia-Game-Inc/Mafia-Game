package mafia.decks.urban

import exceptions.InvalidStateException
import mafia.ConfiguratorService
import mafia.decks.enams.*
import mafia.models.*

class UrbanConfiguratorService: ConfiguratorService {
    private val coefForCalcBlackPlayers = 3
    private val minPlayersAmount = 10
    private val minActiveRolesAmount = 5
    private var activeRoles = listOf(
        RoleData(2, Teams.BLACK, Roles.MAFIA),
        RoleData(1, Teams.BLACK, Roles.GODFATHER),
        RoleData(1, Teams.RED, Roles.SHERIFF),
        RoleData(1, Teams.RED, Roles.DOCTOR)
    )

    override fun configure() {
        val playersAmount = DeckSettings.playersAmount

        if (playersAmount < minPlayersAmount) {
            throw InvalidStateException("Invalid game state: there is not enough players in lobby")
        }

//        DeckSettings.playersAmount = playersAmount
        DeckSettings.blackPlayers =
            playersAmount / coefForCalcBlackPlayers
        DeckSettings.redPlayers =
            playersAmount - DeckSettings.blackPlayers
        // 2 represents sheriff and doctor
        DeckSettings.activePlayersAmount =
            2 + DeckSettings.blackPlayers

        val shortageOfMafias =
            DeckSettings.activePlayersAmount - minActiveRolesAmount
        activeRoles[0].amount += shortageOfMafias

        DeckSettings.activeRoles = activeRoles
    }
}