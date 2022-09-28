package game.mafia.roles

import game.mafia.users.Player

/*
* может такая реализация даже лучше ибо меньше кода всё намного проще и можно
* легко дописывать новые возможности
* проблемы с таким подходом следующие:
* 1. нужен енам или что-то другое чтоб помечать какой роли игрок
* 2. разроботчику нужно знать что мафия именно ибивает(можно решить переименовав функцию в "ход мафии")*/


fun kill(player: Player) {
    TODO("Write logic of killing")
}

fun checkForCom(player: Player) {
    TODO("Write logic of checking for com")
}

fun checkForMafia(player: Player) {
    TODO("Write logic of checking for mafia")
}
