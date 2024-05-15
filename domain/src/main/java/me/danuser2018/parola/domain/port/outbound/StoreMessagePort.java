// SPDX-License-Identifier: MIT

package me.danuser2018.parola.domain.port.outbound;

import lombok.NonNull;

public interface StoreMessagePort {
    void store(@NonNull String hash);
}
