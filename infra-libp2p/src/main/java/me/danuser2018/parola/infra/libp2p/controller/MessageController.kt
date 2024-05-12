// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.controller

import me.danuser2018.parola.infra.libp2p.model.P2pMessage


interface MessageController {
    fun send(message: P2pMessage)
}