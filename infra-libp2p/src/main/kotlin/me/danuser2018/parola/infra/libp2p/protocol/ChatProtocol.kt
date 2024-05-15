// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.protocol

import io.libp2p.core.Stream
import io.libp2p.core.multistream.ProtocolId
import io.libp2p.core.multistream.StrictProtocolBinding
import io.libp2p.protocol.ProtocolHandler
import me.danuser2018.parola.domain.port.inbound.MessageReceivedPort
import me.danuser2018.parola.infra.libp2p.controller.MessageController
import me.danuser2018.parola.infra.libp2p.handler.MessageHandler
import java.util.concurrent.CompletableFuture

typealias ChatBinding = StrictProtocolBinding<MessageController>

const val protocolId: ProtocolId = "/parola/1.0.0"

class ChatProtocol(
    private val messageReceivedPort: MessageReceivedPort
) : ProtocolHandler<MessageController>(Long.MAX_VALUE, Long.MAX_VALUE) {
    override fun onStartInitiator(stream: Stream) = onStart(stream)
    override fun onStartResponder(stream: Stream) = onStart(stream)

    private fun onStart(stream: Stream): CompletableFuture<MessageController> {
        val ready = CompletableFuture<Void>()
        val handler = MessageHandler(ready, messageReceivedPort)
        stream.pushHandler(handler)
        return ready.thenApply { handler }
    }
}

fun chat(
    messageReceivedPort: MessageReceivedPort
): ChatBinding = object : ChatBinding(protocolId, ChatProtocol(messageReceivedPort)) {}
