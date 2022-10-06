package game.mafia.users

import game.mafia.roles.Roles

class Host(base: Player, role: Roles, state: UserState) : Player(base.position, base.id, role, state) {

    fun startGame () {
        println("game started...")
    }

    fun pauseGame () {
        println("game paused...")
    }
}

