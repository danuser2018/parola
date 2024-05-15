// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p

import io.libp2p.core.Host
import me.danuser2018.parola.domain.model.Message
import me.danuser2018.parola.domain.port.outbound.SendMessagePort
import me.danuser2018.parola.domain.port.outbound.StartNodePort
import me.danuser2018.parola.domain.port.outbound.StopNodePort
import me.danuser2018.parola.infra.libp2p.handler.PeerHandler
import me.danuser2018.parola.infra.libp2p.model.toP2pMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class LibP2pNodeAdapter(
    private val host: Host,
    private val peerHandler: PeerHandler
) : StartNodePort, StopNodePort, SendMessagePort {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun startNode() {
        host.start().join()
        log.info("Node [${host.peerId}] started")

        peerHandler.start().join()
        log.info("Looking up for peers ...")
    }

    override fun stopNode() {
        peerHandler.stop().join()
        log.info("Peers look up stopped")

        host.stop().join()
        log.info("Node stopped")
    }

    override fun sendMessage(message: Message) {

        val p2pMessage = message.toP2pMessage()

        for (peerController in peerHandler.peers.values) {
            peerController.send(p2pMessage)
        }
    }
}