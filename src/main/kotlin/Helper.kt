import discord4j.core.`object`.entity.Member
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.voice.AudioProvider

class AudioHelper() {
     companion object {
        fun ensureVoiceChannelJoined(event: MessageCreateEvent, audioProvider: AudioProvider) {
            val member: Member = event.member.orElse(null)
            val voiceState = member.voiceState.block()
            if (voiceState != null) {
                val channel = voiceState.channel.block()
                println("joining ${channel.name}")
                channel?.join {spec-> spec.setProvider(audioProvider)}?.block()
            } else {
                event.message.channel.flatMap {
                        channel -> channel.createMessage("Hawara geh in an voicechannel!")}.block()
            }
        }
    }
}