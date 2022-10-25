package game.mafia.roles

import game.mafia.users.Player

interface PreSet {

    fun configure(players: Int)

    fun runNight(players: HashMap<Roles, Player>)
}