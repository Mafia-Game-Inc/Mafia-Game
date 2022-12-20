package game.mafia.roles

import game.mafia.users.Player

fun checkForMafia(sheriff: Player, players: MutableList<Player>, availablePositions: List<Int>) {
    println("Hey, Sheriff, try to find mafia choose from:")
    println(availablePositions)

    val sheriffChoice = readln().toInt()
    val chosenPlayer = players.find { it.position == sheriffChoice } ?:
    throw IllegalArgumentException("Invalid chosen position: no such position available")

    if (chosenPlayer.role == Roles.SHERIFF) {
        println("You are right!")
    }
    else println("You are wrong!")
}

fun kill(godFather: Player, players: MutableList<Player>, availablePositions: List<Int>) {
    println("Gog father is choosing which player to kill...")

    //в течении какого-то времени капитан черной команды должен ввести выбор

    val godFatherChoice = godFather.chooseFrom(availablePositions)


    // думаю стоит выделить один общий метод выбора позиции в вьюшке и обрнуть его методом
    // плеера и в обёртке реализовать обращение к конктерному пользователю
    // в плеере можно выделить один общий метод выбора
    // как сделать чтоб у не приватной переменной не существовало сеттера
}

fun checkForCom(godFather: Player, players: MutableList<Player>, availablePositions: List<Int>) {
    println("Hey, Don, try to find Sheriff")
    println(availablePositions)

    val godfatherChoice = readln().toInt()
    val chosenPlayer = players.find { it.position == godfatherChoice } ?:
    throw IllegalArgumentException("Invalid chosen position: no such position available")

    if (chosenPlayer.role == Roles.MAFIA || chosenPlayer.role == Roles.GODFATHER) {
        println("You are right!")
    }
    else println("You are wrong!")
}