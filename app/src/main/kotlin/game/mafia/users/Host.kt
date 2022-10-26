package game.mafia.users

/*
* think how to manage access to game state
* so only way would be through Host methods
*/

class Host(base: Player) : Player(base) {

    fun startGame () {
        println("game started...")
    }

    fun pauseGame () {
        println("game paused...")
    }
}

