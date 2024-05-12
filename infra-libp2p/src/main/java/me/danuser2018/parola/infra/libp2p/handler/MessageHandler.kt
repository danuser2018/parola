// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.handler

import io.libp2p.core.Stream
import io.libp2p.etc.types.toByteBuf
import io.libp2p.protocol.ProtocolMessageHandler
import io.netty.buffer.ByteBuf
import me.danuser2018.parola.domain.model.Message
import me.danuser2018.parola.domain.port.inbound.MessageReceivedPort
import me.danuser2018.parola.infra.libp2p.controller.MessageController
import me.danuser2018.parola.infra.libp2p.model.P2pMessage
import java.nio.charset.Charset
import java.util.concurrent.CompletableFuture

class MessageHandler(
    private val ready: CompletableFuture<Void>,
    private val messageReceivedPort: MessageReceivedPort
): ProtocolMessageHandler<ByteBuf>, MessageController {

    private lateinit var stream: Stream

    override fun onActivated(stream: Stream) {
        this.stream = stream
        ready.complete(null)
    }

    override fun onMessage(stream: Stream, msg: ByteBuf) {
        val msgStr = msg.toString(Charset.defaultCharset())
        messageReceivedPort.messageReceived(
            Message("Anonymous", msgStr, System.currentTimeMillis())
        )
    }

    override fun send(message: P2pMessage) {
        val data = message.message.toByteArray(Charset.defaultCharset())
        stream.writeAndFlush(data.toByteBuf())
    }
}