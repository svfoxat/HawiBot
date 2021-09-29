package command

import discord4j.core.event.domain.message.MessageCreateEvent
import music.TrackScheduler

class RemoveCommand(private val trackScheduler: TrackScheduler) : Command {

    override val identifier: String
        get() = "!remove"

    override fun execute(event: MessageCreateEvent, args: List<String>) {
        trackScheduler.removeIndex(args.get(1).toInt())
    }
}