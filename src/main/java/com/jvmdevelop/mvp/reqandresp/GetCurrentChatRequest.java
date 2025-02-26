package com.jvmdevelop.mvp.reqandresp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class GetCurrentChatRequest {
    private Long userId;
}
