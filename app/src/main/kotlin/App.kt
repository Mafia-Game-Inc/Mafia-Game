import mafia.models.DeckSettings
import kotlin.random.Random

open class User {
    val id: UInt = Random.nextUInt()
    var nickName: String = "UserName"

    fun becameSpectator(): Spectator {
        return Spectator()
    }

    fun becamePlayer(): Player {
        val position = choosePosition()
        return Player(position)
    }

    private fun choosePosition(): Int {
        val availablePositions = Lobby.getUnoccupiedPositions()

        View.sendMessage(
            "Please choose position from below list:\n$availablePositions"
        )

        return View.readInt(availablePositions)
    }
}

fun main() {
    val i: Int? = null
    i?.minus(1) ?: throw IllegalArgumentException("")
}