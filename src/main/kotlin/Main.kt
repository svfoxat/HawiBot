import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrameBufferFactory
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer
import command.*
import discord4j.voice.AudioProvider
import music.LavaPlayerAudioProvider
import music.TrackScheduler
import java.util.concurrent.atomic.AtomicBoolean

fun main() {
    val playerManager: AudioPlayerManager = DefaultAudioPlayerManager()

    playerManager.configuration.frameBufferFactory =
        AudioFrameBufferFactory { bufferDuration: Int, format: AudioDataFormat?, stopping: AtomicBoolean? ->
            NonAllocatingAudioFrameBuffer(
                bufferDuration,
                format,
                stopping
            )
        }

    AudioSourceManagers.registerRemoteSources(playerManager)
    val player = playerManager.createPlayer()
    val provider: AudioProvider = LavaPlayerAudioProvider(player)
    val trackScheduler = TrackScheduler(player);

    val bot = Bot(System.getenv("BOT_TOKEN"))

    bot.registerCommand(PingCommand())
    bot.registerCommand(JoinCommand(provider))
    bot.registerCommand(PlayCommand(provider, playerManager, trackScheduler))
    bot.registerCommand(SkipCommand(trackScheduler))
    bot.registerCommand(ClearCommand(trackScheduler))
    bot.registerCommand(ListQueueCommand(trackScheduler))
    bot.registerCommand(RemoveCommand(trackScheduler))

    bot.start()
}