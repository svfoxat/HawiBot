package command

import discord4j.core.event.domain.message.MessageCreateEvent

interface Command {
    val identifier: String
    fun execute(event: MessageCreateEvent, args: List<String>)
}