package com.jvmdevelop.mvp.reqandresp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class SendMessageRequest {
    private Long chatId;
    private String content;
}
