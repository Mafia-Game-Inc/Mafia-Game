package game.mafia.roles

import game.mafia.users.*

interface PreSet {

    fun configure(players: Int)

    fun runNight(players: Array<Player>)
}