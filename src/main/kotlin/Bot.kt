import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import command.Command
import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.voice.AudioProvider

class Bot constructor(private val token: String) {
    private var commands: MutableCollection<Command> = mutableListOf()

    fun registerCommand(cmd: Command) {
        commands.add(cmd)
    }

    private fun printCommands() {
        commands.map { println(it.javaClass) }
    }

    fun start() {
        printCommands()

        val client = DiscordClient.create(token)

        client.withGateway { gateway ->
            gateway.on(MessageCreateEvent::class.java)
                .map { event -> parseMessage(event) }
        }.block()
    }

    private fun parseMessage(event: MessageCreateEvent) {
        val args = listOf(*event.message.content.split(" ").toTypedArray())
        commands.find { command -> args[0] == command.identifier }?.execute(event, args)
    }
}