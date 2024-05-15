// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.libp2p.core.Stream
import io.libp2p.etc.types.toByteBuf
import io.libp2p.protocol.ProtocolMessageHandler
import io.netty.buffer.ByteBuf
import me.danuser2018.parola.domain.port.inbound.MessageReceivedPort
import me.danuser2018.parola.infra.libp2p.controller.MessageController
import me.danuser2018.parola.infra.libp2p.model.P2pMessage
import me.danuser2018.parola.infra.libp2p.model.toMessage
import java.nio.charset.Charset
import java.util.concurrent.CompletableFuture

class MessageHandler(
    private val ready: CompletableFuture<Void>,
    private val messageReceivedPort: MessageReceivedPort
) : ProtocolMessageHandler<ByteBuf>, MessageController {

    companion object {
        private val mapper = ObjectMapper().registerModule(kotlinModule())
    }

    private lateinit var stream: Stream

    override fun onActivated(stream: Stream) {
        this.stream = stream
        ready.complete(null)
    }

    override fun onMessage(stream: Stream, msg: ByteBuf) {
        val p2pMessage: P2pMessage = mapper.readValue(msg.toString(Charset.defaultCharset()))
        messageReceivedPort.messageReceived(p2pMessage.toMessage())
    }

    override fun send(message: P2pMessage) {
        val data = mapper.writeValueAsBytes(message)
        stream.writeAndFlush(data.toByteBuf())
    }
}