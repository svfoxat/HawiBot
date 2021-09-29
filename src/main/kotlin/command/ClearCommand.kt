package command

import discord4j.core.event.domain.message.MessageCreateEvent
import music.TrackScheduler


class ClearCommand(private val trackScheduler: TrackScheduler) : Command {
    override val identifier: String
        get() = "!clear"

    override fun execute(event: MessageCreateEvent, args: List<String>) {
        trackScheduler.clearQueue();
    }
}