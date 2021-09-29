package command

import music.TrackScheduler
import discord4j.core.event.domain.message.MessageCreateEvent


class SkipCommand(private val trackScheduler: TrackScheduler) : Command {

    override val identifier: String
        get() = "!skip"

    override fun execute(event: MessageCreateEvent, args: List<String>) {
        println(event.message.content)
        trackScheduler.nextTrack()
    }
}
