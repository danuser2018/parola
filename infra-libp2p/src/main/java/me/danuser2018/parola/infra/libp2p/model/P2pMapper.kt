// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.model

import me.danuser2018.parola.domain.model.Message


fun Message.toP2pMessage(): P2pMessage = P2pMessage(nickName, message, timestamp)
fun P2pMessage.toMessage(): Message = Message(nickName, message, timestamp)