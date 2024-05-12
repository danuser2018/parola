// SPDX-License-Identifier: MIT

package me.danuser2018.parola.domain.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class Message {
    @NonNull String nickName;
    @NonNull String message;
    long timestamp;
}
