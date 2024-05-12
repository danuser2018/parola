// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.model

data class P2pMessage(
    val nickName: String,
    val message: String,
    val timestamp: Long
)
