package game.view

import kotlinx.coroutines.processNextEventInCurrentThread
import java.util.concurrent.TimeoutException

/*
* 1. how to limit time for console input
*/
val MAX_INPUT_LENGTH = 100;
const val FORBIDDEN_CHARACTERS = "!@#$%^&*()"

fun log(message: String) {
    val currentTime = System.currentTimeMillis()
    val logMessage = "[$currentTime] $message"
    // Здесь можете разместить код, который будет записывать logMessage в лог-файл или отправлять его в систему мониторинга
    println(logMessage)
}

fun processInput(inputString: String, timeRemaining: Long): String {
    if (timeRemaining <= 0) {
        throw TimeoutException("Time limit reached")
    }

    if (inputString.length > MAX_INPUT_LENGTH) {
        throw IllegalArgumentException("Input exceeded maximum length")
    }

    if (inputString.contains(FORBIDDEN_CHARACTERS)) {
        throw IllegalArgumentException("Input contains forbidden characters")
    }

    return inputString
}

fun processInputWithErrorHandling(inputString: String, timeRemaining: Long, errorHandler: (String) -> Unit): String {
    try {
        val processedInput = processInput(inputString, timeRemaining)
        log("Input processed successfully: $processedInput")
        return processedInput
    } catch (e: TimeoutException) {
        errorHandler("TimeoutException: ${e.message}")
        log("TimeoutException occurred: ${e.message}")
    } catch (e: IllegalArgumentException) {
        errorHandler("IllegalArgumentException: ${e.message}")
        log("IllegalArgumentException occurred: ${e.message}")
    }
    return ""
}

//
//fun sendMessage(message: String) {
//    println(message)
//}
//
//fun readBoolean(): Boolean {
//    var input = readln()
//
//    while (input != "false" && input != "true") {
//        sendMessage("the value should be 'false' or 'true', try again:")
//        input = readln()
//    }
//
//    return input.toBoolean()
//}

//fun readInt(permissibleValues: List<Int>): Int {
//    var input: Int
//    try {
//        input = readln().toInt()
//    } catch (e: NumberFormatException) {
//        //?
//    }
//
//    while (!permissibleValues.contains(input)) {
//        sendMessage("the value should one of given below")
//        sendMessage("$permissibleValues")
//        sendMessage("try again:")
//        //здесь тоже словить ошибку
//        input = readln().toInt()
//    }
//
//    return input
//}