package music

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack


class TrackLoader(private val scheduler: TrackScheduler) : AudioLoadResultHandler {
    override fun trackLoaded(track: AudioTrack) {
        println("music.TrackLoader::trackLoaded")
        scheduler.queueTrack(track);
    }

    override fun playlistLoaded(playlist: AudioPlaylist) {
        println("music.TrackLoader::playlistLoaded")
        playlist.tracks.map { track -> scheduler.queueTrack(track) }
        println("music.TrackLoader added ${playlist.tracks.size} tracks to q")
    }

    override fun noMatches() {
        println("music.TrackLoader::noMatches")
    }

    override fun loadFailed(exception: FriendlyException) {
        println("music.TrackLoader::loadFailed")
    }
}