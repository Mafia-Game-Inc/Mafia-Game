package game.view

import game.exceptions.*
import game.mafia.users.Player
import game.mafia.users.UserStates

object GameView {
    private const val MaxInputLength = 100
    const val forbiddenCharacters = "!@#$%^&*()"
    private val players = mutableListOf<Player>()

    private fun hasForbiddenCharacters(input: String, forbidden: String): Boolean {
        return input.any { char -> forbidden.contains(char) }
    }

    private fun log(message: String) {
        val currentTime = System.currentTimeMillis()
        val logMessage = "[$currentTime] $message"
        // code to write logMessage to a log file or send it to a monitoring system
    }

    fun printLogs() {
        // code to output data from a log file
    }

    private fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }

    fun sendMessageToAllPlayers(message: String) {
        for (player in players) {
            val currentTime = getCurrentTime()
            val messageWithTimeMark = "[$currentTime] $message"
            println(messageWithTimeMark)
        }
    }

    private fun sendMessageToAllAlive(message: String) {
        for (player in players) {
            if (player.state == UserStates.ALIVE) {
                val currentTime = getCurrentTime()
                val messageWithTimeMark = "[$currentTime] $message"
                println(messageWithTimeMark)
            }
        }
    }

    private fun sendMessageToPlayer(message: String) {
        val currentTime = getCurrentTime()
        val messageWithTimeMark = "[$currentTime] $message"
        println(messageWithTimeMark)
        // logic to work with individual user views here
    }

    private fun processMessageString(message: String): String {
        if (message.length > GameView.MaxInputLength) {
            throw InvalidLengthException("Input exceeded maximum length")
        }
        val result = GameView.hasForbiddenCharacters(message, GameView.forbiddenCharacters)
        if (result) {
            throw InvalidCharException("Input contains forbidden characters")
        }
        return message
    }

    private fun processMessageInt(message: String, permissibleValues: List<Int>): Int{
        val messageInt = message.toInt()
        if (!permissibleValues.contains(messageInt)) {
            throw InvalidCharException("Input contains forbidden characters")
        }
        return messageInt
    }

    private fun processMessageBoolean(message: String): String {
        if (message != "false" && message != "true") {
            throw InvalidCharException("Input contains forbidden characters")
        }

        return message
    }

    fun readString(): String {
        try {
            val message = readln()
            val processedInput = GameView.processMessageString(message)
            GameView.log("Input processed successfully: $processedInput")
            GameView.sendMessageToAllAlive(processedInput)
        } catch (e: InvalidLengthException) {
            GameView.log("InvalidLengthException occurred: ${e.message}")
        } catch (e: InvalidCharException) {
            GameView.log("InvalidCharException occurred: ${e.message}")
        }
        GameView.sendMessageToPlayer("Invalid input, please try again")
        return readString()
    }


    fun readBoolean(): Boolean {
        try {
            val message = readln()
            val processedInput = GameView.processMessageBoolean(message)
            GameView.log("Input processed successfully: $processedInput")
            return processedInput.toBoolean()
        } catch (e: InvalidStateException) {
            GameView.log("InvalidLengthException occurred: ${e.message}")
            // Тут нужно придумать, как в случае, если воод не правильный, но время ещё есть
            // нам нужно ожидать следующего ввода пользователя
        }
        GameView.sendMessageToPlayer("Invalid input, please try again")
        return readBoolean()
    }


    fun readInt(permissibleValues: List<Int>): Int {
        try {
            val message = readln()
            val processedInput = GameView.processMessageInt(message, permissibleValues)
            GameView.log("Input processed successfully: $processedInput")
            return processedInput
        } catch (e: InvalidStateException) {
            GameView.log("InvalidLengthException occurred: ${e.message}")
            // Тут нужно придумать, как в случае, если воод не правильный, но время ещё есть
            // нам нужно ожидать следующего ввода пользователя
        }
        GameView.sendMessageToPlayer("Invalid input, please try again")
        return readInt(permissibleValues)
    }
}