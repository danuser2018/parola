// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.libp2p.config

import io.libp2p.core.Discoverer
import io.libp2p.core.Host
import io.libp2p.core.dsl.host
import io.libp2p.discovery.MDnsDiscovery
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetAddress

@Configuration
open class LibP2pConfig {
    @Bean
    open fun host(): Host = host {
        protocols { }
        network { listen("/ip4/127.0.0.1/tcp/0") }
    }

    @Bean
    open fun peerFinder(host: Host): Discoverer = MDnsDiscovery(
        host, address = InetAddress.getLocalHost()
    )
}