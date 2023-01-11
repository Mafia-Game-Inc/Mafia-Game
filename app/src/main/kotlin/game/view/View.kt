package game.view

import game.exceptions.InvalidCharException
import game.exceptions.InvalidLengthException
import game.exceptions.InvalidStateException
import game.mafia.users.Player
import game.mafia.users.UserStates
import java.util.concurrent.TimeoutException

/*
* 1. how to limit time for console input
*/
val MAX_INPUT_LENGTH = 100;
const val forbiddenCharacters = "!@#$%^&*()"
private val players = mutableListOf<Player>()

fun hasForbiddenCharacters(input: String, forbidden: String): Boolean {
    return input.any { char -> forbidden.contains(char) }
}

fun log(message: String) {
    val currentTime = System.currentTimeMillis()
    val logMessage = "[$currentTime] $message"
    // Здесь можете разместить код, который будет записывать logMessage в лог-файл или отправлять его в систему мониторинга
    println(logMessage)
}

fun processInputString(inputString: String): String {

    if (inputString.length > MAX_INPUT_LENGTH) {
        throw InvalidLengthException("Input exceeded maximum length")
    }

    val result = hasForbiddenCharacters(inputString, forbiddenCharacters)
    if (result) {
        throw InvalidCharException("Input contains forbidden characters")
    }

    return inputString
}

fun sendMessageToAllAlive(inputString: String) {
    for (player in players) {
        if (player.state == UserStates.ALIVE) {
            println(inputString)
        }
    }
}

fun readString(inputString: String, errorHandler: (String) -> Unit) {
    try {
        val processedInput = processInputString(inputString)
        log("Input processed successfully: $processedInput")
        sendMessageToAllAlive(processedInput)
    } catch (e: InvalidLengthException) {
        errorHandler("InvalidLengthException: ${e.message}")
        log("InvalidLengthException occurred: ${e.message}")
    } catch (e: InvalidCharException) {
        errorHandler("InvalidCharException: ${e.message}")
        log("InvalidCharException occurred: ${e.message}")
    }
}

fun sendMessageToAllPlayers(inputString: String) {
    for (player in players) {
        println(inputString)
    }
}

fun sendMessagePlayer(inputString: String, player: Player) {
    println(inputString)
    // Потом сюда, как и в другие функции вывода изображений, нужно будет
    // добавить логику работы с отдельными пользователями
    // Как именно мы будем общаться с ними и какие запросы куда отправлять
}

fun processInputBoolean(inputString: String): String {

    if (inputString != "false" && inputString != "true") {
        throw InvalidCharException("Input contains forbidden characters")
    }

    return inputString
}

fun readBoolean(inputString: String, errorHandler: (String) -> Unit): Any {
    try {
        val processedInput = processInputBoolean(inputString)
        log("Input processed successfully: $processedInput")
        return processedInput.toBoolean()
    } catch (e: InvalidStateException) {
        errorHandler("InvalidLengthException: ${e.message}")
        log("InvalidLengthException occurred: ${e.message}")
        // Тут нужно придумать, как в случае, если воод не правильный, но время ещё есть
        // нам нужно ожидать следующего ввода пользователя
    }
    return ""
}


fun processInputInt(input: String, permissibleValues: List<Int>): Int{

    val inputInt = input.toInt()
    if (!permissibleValues.contains(inputInt)) {
        throw InvalidCharException("Input contains forbidden characters")
    }

    return inputInt
}

fun readInt(input: String, permissibleValues: List<Int>, errorHandler: (String) -> Unit): Any {
    try {
        val processedInput = processInputInt(input, permissibleValues)
        log("Input processed successfully: $processedInput")
        return processedInput
    } catch (e: InvalidStateException) {
        errorHandler("InvalidLengthException: ${e.message}")
        log("InvalidLengthException occurred: ${e.message}")
        // Тут нужно придумать, как в случае, если воод не правильный, но время ещё есть
        // нам нужно ожидать следующего ввода пользователя
    }
    return ""
}