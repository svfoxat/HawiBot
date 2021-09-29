package music

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import java.util.concurrent.LinkedBlockingQueue


class TrackScheduler(private val player: AudioPlayer) : AudioEventAdapter() {
    val queue: LinkedBlockingQueue<AudioTrack> = LinkedBlockingQueue<AudioTrack>()

    init {
        player.addListener(this)
    }

    fun queueTrack(track: AudioTrack) {
        println("music.TrackScheduler::queue")
        if (!player.startTrack(track, true)) {
            queue.offer(track)
        }
    }

    fun nextTrack() {
        println("music.TrackScheduler::nextTrack")
        player.startTrack(queue.poll(), false)
    }

    fun clearQueue() {
        println("music.TrackScheduler::clearQueue")
        player.stopTrack();
        queue.clear();
    }

    fun removeIndex(index: Int) {
        println("music.TrackScheduler::removeIndex")
        queue.remove(queue.elementAt(index))
    }

    override fun onTrackEnd(player: AudioPlayer?, track: AudioTrack?, endReason: AudioTrackEndReason) {
        println("music.TrackScheduler::onTrackEnd")
        if (endReason.mayStartNext) {
            nextTrack()
        }
    }

    override fun onTrackStart(player: AudioPlayer?, track: AudioTrack?) {
        println("music.TrackScheduler::onTrackStart")
    }
}