package command

import music.TrackLoader
import music.TrackScheduler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.voice.AudioProvider


class PlayCommand(private val audioProvider: AudioProvider,
                  private val audioManager: AudioPlayerManager,
                  private val trackScheduler: TrackScheduler,
) : Command {

    override val identifier: String
        get() = "!play"

    override fun execute(event: MessageCreateEvent, args: List<String>) {
        println(event.message.content)
        AudioHelper.ensureVoiceChannelJoined(event, audioProvider)

        audioManager.loadItem(args.get(1), TrackLoader(trackScheduler))
    }

}

