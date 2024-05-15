// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.cache.guava;

import com.google.common.cache.Cache;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.domain.port.outbound.IsMessageStoredPort;
import me.danuser2018.parola.domain.port.outbound.StoreMessagePort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GuavaCacheAdapter implements StoreMessagePort, IsMessageStoredPort {

    private final Cache<String, Boolean> cache;

    @Override
    public boolean isStored(@NonNull String hash) {
        return cache.getIfPresent(hash) != null;
    }

    @Override
    public void store(@NonNull String hash) {
        cache.put(hash, true);
    }
}
