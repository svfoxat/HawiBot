package command

import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.spec.EmbedCreateFields
import discord4j.core.spec.EmbedCreateSpec
import music.TrackScheduler
import java.util.concurrent.TimeUnit

class ListQueueCommand(private var trackScheduler: TrackScheduler) : Command {
    override val identifier: String
        get() = "!list"

    override fun execute(event: MessageCreateEvent, args: List<String>) {
        val channel = event.message.channel.block()


        val embedCreateSpec = EmbedCreateSpec.builder()
            .title("Queue")

        trackScheduler.queue.mapIndexed { idx ,track ->
            embedCreateSpec.addField(EmbedCreateFields.Field.of(
                "$idx ${track.info.title} ${formatMilliseconds(track.duration)}", track.info.uri, false
            ))
        }

        val embed = embedCreateSpec.build()
        channel.createMessage(embed).block()
    }
}


fun formatMilliseconds(millis: Long): String {
    return String.format("%02d:%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(millis),
        TimeUnit.MILLISECONDS.toMinutes(millis) -
        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
        TimeUnit.MILLISECONDS.toSeconds(millis) -
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
}