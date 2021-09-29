package music

import com.sedmelluq.discord.lavaplayer.format.StandardAudioDataFormats
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame
import discord4j.voice.AudioProvider
import java.nio.ByteBuffer


class LavaPlayerAudioProvider(player: AudioPlayer) : AudioProvider(
    ByteBuffer.allocate(
        StandardAudioDataFormats.DISCORD_OPUS.maximumChunkSize()
    )
) {
    private val player: AudioPlayer
    private val frame = MutableAudioFrame()
    override fun provide(): Boolean {
        val didProvide = player.provide(frame)
        if (didProvide) {
            buffer.flip()
        }
        return didProvide
    }

    init {
        frame.setBuffer(buffer)
        this.player = player
    }
}