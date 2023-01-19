import controller.Controller
import mafia.Mafia
import mafia.models.Lobby
import mafia.users.User

fun main() {
    val testMafia = Mafia()
    val testUsers = MutableList(10) { User() }

    for (player in testUsers) {
        Controller.addPlayer(player)
    }

    Controller.play()
//    var i = 1
//    for (user in testUsers) {
//        user.mockToPlayerState(i)
//        Lobby.players[user.position] = user
//        i++
//    }
//
//    testMafia.run()
}