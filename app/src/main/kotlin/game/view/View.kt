package game.view

import kotlinx.coroutines.processNextEventInCurrentThread

/*
* 1. how to limit time for console input
*/

fun log(message: String) {
    println(message)
}

fun sendMessage(message: String) {
    println(message)
}

fun readBoolean(): Boolean {
    var input = readln()

    while (input != "false" && input != "true") {
        sendMessage("the value should be 'false' or 'true', try again:")
        input = readln()
    }

    return input.toBoolean()
}

fun readInt(permissibleValues: List<Int>): Int {
    var input: Int
    try {
        input = readln().toInt()
    } catch (e: NumberFormatException) {
        //?
    }

    while (!permissibleValues.contains(input)) {
        sendMessage("the value should one of given below")
        sendMessage("$permissibleValues")
        sendMessage("try again:")
        //здесь тоже словить ошибку
        input = readln().toInt()
    }

    return input
}