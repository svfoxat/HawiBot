package command

import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.voice.AudioProvider


class JoinCommand(private val audioProvider: AudioProvider) : Command {
    override val identifier: String
        get() = "!join"

    override fun execute(event: MessageCreateEvent, args: List<String>) {
        AudioHelper.ensureVoiceChannelJoined(event, audioProvider)
    }
}