// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.handler

import io.libp2p.core.*
import me.danuser2018.parola.domain.port.inbound.MessageReceivedPort
import me.danuser2018.parola.infra.libp2p.controller.MessageController
import me.danuser2018.parola.infra.libp2p.protocol.chat
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class PeerHandler(
    private val host: Host,
    private val peerFinder: Discoverer,
    private val messageReceivedPort: MessageReceivedPort
) {

    private val log = LoggerFactory.getLogger(javaClass)
    private val knownNodes: MutableSet<PeerId> = mutableSetOf()
    val peers: MutableMap<PeerId, MessageController> = mutableMapOf()

    init {
        host.addProtocolHandler(chat(messageReceivedPort))
        peerFinder.newPeerFoundListeners += ::peerFound
    }

    fun start(): CompletableFuture<Void> = peerFinder.start()
    fun stop(): CompletableFuture<Void> = peerFinder.stop()

    private fun peerFound(peerInfo: PeerInfo) {
        if (peerInfo.peerId == host.peerId || knownNodes.contains(peerInfo.peerId)) {
            return
        }
        log.info("Found new peer [${peerInfo.peerId}]")

        val (stream, controller) = openConnection(peerInfo) ?: return

        stream.addDisconnectionHandler {
            peers.remove(peerInfo.peerId)
            knownNodes.remove(peerInfo.peerId)
            log.info("Node [${peerInfo.peerId} disconnected")
        }

        peers[peerInfo.peerId] = controller
    }

    private fun openConnection(peerInfo: PeerInfo): Pair<Stream, MessageController>? {
        try {
            val chat = chat(messageReceivedPort).dial(host, peerInfo.peerId, peerInfo.addresses[0])
            return Pair(
                chat.stream.get(),
                chat.controller.get()
            )
        } catch (e: Exception) {
            log.error("There was an error opening a connection with Node [${peerInfo.peerId}]: ${e.message}", e)
            return null
        }
    }

    private fun Stream.addDisconnectionHandler(handler: (Unit) -> Unit) {
        closeFuture().thenAccept(handler)
    }
}