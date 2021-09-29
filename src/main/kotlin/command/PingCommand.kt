package command

import discord4j.core.`object`.entity.Message
import discord4j.core.event.domain.message.MessageCreateEvent

class PingCommand : Command {
    override val identifier: String
        get() = "!ping"

    override fun execute(event: MessageCreateEvent, args: List<String>) {
        event.message.channel.flatMap { channel -> channel.createMessage("pong")}.block()
    }
}