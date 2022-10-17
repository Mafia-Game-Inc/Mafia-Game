package game.mafia.roles

import game.mafia.users.Player

interface PreSet {
    fun runNight(players: HashMap<Roles, Player>) {}
}