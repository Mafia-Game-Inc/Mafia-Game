package mafia

interface SuspendableService {
    fun pause()

    fun resume()
}