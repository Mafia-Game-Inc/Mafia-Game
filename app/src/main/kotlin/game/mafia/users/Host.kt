package game.mafia.users

class Host(base: Player) : Player(base) {

    fun startGame () {
        println("game started...")
    }

    fun pauseGame () {
        println("game paused...")
    }
}

