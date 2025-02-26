package com.jvmdevelop.mvp.reqandresp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class DeleteMessageRequest {
    private Long messageId;

}
